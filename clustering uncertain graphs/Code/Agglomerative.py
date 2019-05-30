import numpy as np
import ast

class Cluster:
    pSize = 0
    def __init__(self,id,value):
        self.id = id
        self.value = [value]
        self.size = len(self.value)
        Cluster.pSize += 1

#merge 2 cluster
def MergeCluster(a, b):
    a.value = a.value + b.value
    a.size = len(a.value)
    Cpool.pop(b.id)
    if b.id == Cluster.pSize:
        pass
    else:
        for cluster in Cpool:
            if cluster.id > b.id:
                cluster.id -= 1
    Cluster.pSize -= 1

#takes in 2 cluster a,b, return average edge probability between them
def AvgEdgeProb(a,b):
        prob = 0
        for i in a.value:
            for j in b.value:
                prob = prob + Wmatrix[i][j]
        prob = prob/(a.size*b.size)
        return prob

def EditDistance(pool):
    Distance = 0
    for cluster in pool:
        for cluster2 in pool:
            if cluster2.id == cluster.id:
                for i in cluster.value:
                    for j in cluster.value:
                        Distance += (1 - Wmatrix[i][j]) / 2
            else:
                for m in cluster.value:
                    for n in cluster2.value:
                        Distance += Wmatrix[m][n]/2
    return Distance

#read data and generating weightmatix Wmatrix
fp = open("Data/TAP.txt")
orig = fp.readlines()[0:]
fp.close()
lines = [l.split() for l in orig]
lines1, lines2, prob = [],[],[]
for x,y,z in lines:
    lines1.append(x)
    lines2.append(y)
    prob.append(z)
prob = list(map(float, prob))
protein = []
for p in lines1:
    if p not in protein:
        protein.append(p)
for p in lines2:
    if p not in protein:
        protein.append(p)
proNum = len(protein)
Wmatrix = np.zeros((proNum, proNum))
proindex = {}
for i in range(proNum):
    proindex[protein[i]] = i
pairnum = len(orig)
for i in range(pairnum):
    x, y = proindex[lines1[i]], proindex[lines2[i]]
    Wmatrix[x][y] = prob[i]
    Wmatrix[y][x] = prob[i]
for i in range(proNum):
    Wmatrix[i][i] = 1

if __name__ == '__main__':
    #poolSize = proNum
    poolSize = len(Wmatrix)
    Cpool = []
    for i in range(poolSize):
        Cpool.append(Cluster(i,i))
    MaxProb = 1
    while MaxProb >= 0.5:
        MaxProb = 0
        for cluster1 in Cpool:
            for cluster2 in Cpool:
                if cluster2.id > cluster1.id:
                    prob = AvgEdgeProb(cluster1,cluster2)
                    if prob > MaxProb:
                        MaxProb = max(MaxProb,prob)
                        c1, c2 = cluster1, cluster2
        if MaxProb >= 0.5:
            MergeCluster(c1, c2)
    EDis = EditDistance(Cpool)

   #nonsingleton cluster
    Npool = []
    for clus in Cpool:
        if len(clus.value) > 1:
            Npool.append(clus)
    NEDis = EditDistance(Npool)

    opf = open("tapcore_agg", "w")
    for cluster in Cpool:
        opf.write("cluster%d:%s\n" % (cluster.id, [protein[i] for i in cluster.value]))
    opf.write("Edit_Distance is %f, cluster number is %d\n" % (EDis,len(Cpool)))
    opf.close()

    f2 = open("tapcore_agg_nsingle", "w")
    for cluster in Npool:
        f2.write("ncluster%d:%s\n" % (cluster.id, [protein[i] for i in cluster.value]))
    f2.write("Edit_Distance for nonsingle cluster is %f, nonsingle cluster number is %d\n" % (NEDis, len(Npool)))
    f2.close()
