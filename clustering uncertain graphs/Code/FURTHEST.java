package Practise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class FURTHEST {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i2=11;i2<15;i2++){
		String partFileName = String.format("%s%d.txt", "resultsOfSecondAlg", i2);
 
		int n = 1622;
		int numLines = 0;//count how many lines
		int lengthLine=0;
		int numGreater=0;
		LinkedList<String> mapList;//store names
		PrintWriter pr=null;
	
		double[][] arr = new double[n][n];//generate ajacency matrix
		int maxSize=0;
		int minSize=0;
		double distance=0;
		try {
			/** read names **/
			//read mapping names from the file, from zero to correspond
			Scanner scan2=new Scanner(new File("mappingcollins.txt"));
			String mapResult = scan2.nextLine();
			String[] mapArray = mapResult.split(",");
			lengthLine=mapArray.length;//how many names
			mapList=new LinkedList<>(Arrays.asList(mapArray));//store names in list from arrays
			scan2.close();
			
			/** read original data as matrix **/
			//read original probability data
			Scanner scan = new Scanner(new File("WMcollins.txt"));
			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				String[] a = s.split(" ");
				lengthLine=a.length;
				for(int i1=0;i1<lengthLine;i1++)
					arr[numLines][i1]=Double.parseDouble(a[i1]);
				numLines++;
			}
			//make a[i][i] equals to 1
			for(int i1=0;i1<lengthLine;i1++)
				arr[i1][i1]=1;
			scan.close();
			
			/** print some basic information about the data **/
			System.out.println("length of each line is:"+lengthLine);
			System.out.println("num of lines is:"+numLines);
			
			/** Start implementing algorithms**/
			pr=new PrintWriter(new FileOutputStream(new File(partFileName),true));
			LinkedList<LinkedList> centerSet=new LinkedList<>();//store centers
			
			LinkedList<Integer> centers=new LinkedList<>();
			

			
			int numOfIteration=0;//record how many iteration has been played
			boolean invalid=true;
			double minProb=1;//initialize the minimum prob to 1
			int rowOfFirstCenter=-1;
			int colOfFirstCenter=-1;
			
			while(invalid){//orginal set is not empty and edit distance is decreasing
				if(numOfIteration==0){//first iteration
					LinkedList<Integer> originalSet=new LinkedList<>();
					for(int i1=0;i1<numLines;i1++)//initialze original set of nodes
						originalSet.add(i1);
					
					for(int i=0;i<numLines;i++)
						for(int j=0;j<numLines;j++){
							if(arr[i][j]<minProb&&arr[i][j]!=0){
								minProb=arr[i][j];
								rowOfFirstCenter=i;
								colOfFirstCenter=j;
							}
						}
						
					
					
					LinkedList<Integer> c0=new LinkedList<>();//add nodes to the set of first centers
					c0.add(rowOfFirstCenter);
					centerSet.add(c0);//add first cluster to the set of cluster
					LinkedList<Integer> c1=new LinkedList<>();
					c1.add(colOfFirstCenter);
					centerSet.add(c1);
					centers.add(rowOfFirstCenter);//add centers
					centers.add(colOfFirstCenter);
					originalSet.remove(new Integer(rowOfFirstCenter));//remove nodes form the set of original nodes
					originalSet.remove(new Integer(colOfFirstCenter));
					
					LinkedList<Integer> copyOfOriginal=(LinkedList<Integer>)originalSet.clone();//clone to prevent original one being destroyed
					LinkedList<LinkedList> copyOfCenterSet=(LinkedList<LinkedList>)centerSet.clone();
					for(int col:originalSet){//start to assign nodes
						int row=-1;
						double max=0;
						int bestCol=-1,bestRow=-1;
						for(int i=0;i<copyOfCenterSet.size();i++){
							row=(int)copyOfCenterSet.get(i).getFirst();
							if(arr[row][col]>max&&(row!=col)){
								max=arr[row][col];
								bestRow=row;
								bestCol=col;//find which row and which col
							}//end of if
							
								}//end of for	
						if(bestCol!=-1){//if truly find a col
							for(int j=0;j<copyOfCenterSet.size();j++){
								if((int)copyOfCenterSet.get(j).getFirst()==bestRow){
									copyOfCenterSet.get(j).add(bestCol);
									copyOfOriginal.remove(new Integer(bestCol));//remove nodes which has been assigned from the set of nodes
								}//end of if
								}	//end of if		
						}//end of for
					}//end of for, and end of assigning nodes
					
					for(int node:copyOfOriginal){
						LinkedList<Integer> temp=new LinkedList<>();
						temp.add(node);
						copyOfCenterSet.add(temp);
					}
					
					for(int i=0;i<copyOfCenterSet.size();i++)
						System.out.println(copyOfCenterSet.get(i).toString());
					distance=editDistance(copyOfCenterSet, arr);
					System.out.println("edit distance:"+distance);
					System.out.println("count:"+numOfIteration);
					centerSet=copyOfCenterSet;
					originalSet=copyOfOriginal;
					numOfIteration++;
				}//end of if
				
				/** else **/
				
				else{
					double previousDistance=distance;
					LinkedList<Integer> originalSet=new LinkedList<>();
					for(int i1=0;i1<numLines;i1++)//initialze original set of nodes
						originalSet.add(i1);
					LinkedList<LinkedList> copyOfCenterSet=new LinkedList<>();
					for(int i=0;i<centers.size();i++){
						LinkedList<Integer> temp=new LinkedList<>();
						temp.add(centers.get(i));
						copyOfCenterSet.add(temp);
						originalSet.remove(new Integer(centers.get(i)));
					}
					LinkedList<Integer> copyOfOriginal=(LinkedList<Integer>)originalSet.clone();//clone to prevent original one being destroyed

					LinkedList<Integer> index=new LinkedList<>();
					LinkedList<Double> correspondingProb=new LinkedList<>();
					for(int col:copyOfOriginal){
						double maxProb=0;
						index.add(col);
						for(int i=0;i<copyOfCenterSet.size();i++){				
							int row=(int)copyOfCenterSet.get(i).getFirst();
							if(arr[row][col]>=maxProb){
								maxProb=arr[row][col];
							}//end of if			
						}//end of for
						correspondingProb.add(maxProb);
					}//end of out for
					double minimumProb=1;
					int indexOfMinimum=-1;
					for(int i=0;i<correspondingProb.size();i++){
						if(correspondingProb.get(i)<=minimumProb){
							minimumProb=correspondingProb.get(i);
						}
					}
					
					LinkedList<Integer> indexOfSameMinimum=new LinkedList<>();
					for(int i=0;i<correspondingProb.size();i++){
						if(correspondingProb.get(i)==minimumProb){
							indexOfSameMinimum.add(index.get(i));
						}
					}
					
					Random rand=new Random();
					int index1=rand.nextInt(indexOfSameMinimum.size());
					indexOfMinimum=indexOfSameMinimum.get(index1);//found the index of the node which has the farthest distance from all centers
					centers.add(indexOfMinimum);
					LinkedList<Integer> c=new LinkedList<>();
					c.add(indexOfMinimum);
					copyOfCenterSet.add(c);
					copyOfOriginal.remove(new Integer(indexOfMinimum));//remove nodes form the set of original nodes
					
					//LinkedList<Integer> copyOfOriginal2=(LinkedList<Integer>)copyOfOriginal.clone();
					for(int col:originalSet){//start to assign nodes
						int row=-1;
						double max=0;
						int bestCol=-1,bestRow=-1;
						int i=0;
						for(i=0;i<copyOfCenterSet.size();i++){
							row=(int)copyOfCenterSet.get(i).getFirst();
							if(arr[row][col]>max&&(row!=col)){//cannot be zero prob
								max=arr[row][col];
								bestRow=row;
								bestCol=col;//find which row and which col
							}//end of if			
						}//end of for
						if(bestCol!=-1){//if truly find a col
							for(int j=0;j<copyOfCenterSet.size();j++){
								if((int)copyOfCenterSet.get(j).getFirst()==bestRow){
									copyOfCenterSet.get(j).add(bestCol);
									copyOfOriginal.remove(new Integer(bestCol));//remove nodes which has been assigned from the set of nodes
								}//end of if
							}//end of for		
							}	//end of if	
					}//end of for, and end of assigning nodes
					
					for(int node:copyOfOriginal){
						LinkedList<Integer> temp=new LinkedList<>();
						temp.add(node);
						copyOfCenterSet.add(temp);
					}
					
					for(int i=0;i<copyOfCenterSet.size();i++)
						System.out.println(copyOfCenterSet.get(i).toString());
					distance=editDistance(copyOfCenterSet, arr);
					System.out.println("edit distance:"+distance);
					
					if(distance>previousDistance){
						int maxSize2=0;
						int check=0;
						for(LinkedList<Integer> cluster:centerSet){
							check+=cluster.size();
							if(cluster.size()>maxSize2){
								maxSize2=cluster.size();
							}
							LinkedList<String> names=new LinkedList<>();
							for(int i=0;i<cluster.size();i++){
								names.add(mapList.get(cluster.get(i)));
							}
							pr.println(names.toString());
						}
						pr.println("num of clusters:"+centerSet.size());
						pr.println("chekc nodes:"+check);
						pr.println("max size of cluster is:"+maxSize2);
						pr.println("edit distance is:"+distance);
						pr.close();
						invalid=false;}
					else{
						centerSet=copyOfCenterSet;
						originalSet=copyOfOriginal;
					}
					
					
					System.out.println("count:"+numOfIteration);
					numOfIteration++;
				}//end of else
			}//end of while
			}catch(Exception ex){System.out.println(ex.getMessage()+ex.getLocalizedMessage()+ex.toString());}}//end of for
			

	}

	
	public static double editDistance(LinkedList<LinkedList> clusters,double[][] originalMatrix){
		double sum=0;
		double partSum=0;
		for(LinkedList<Integer> element1:clusters){
			for(LinkedList<Integer> element2:clusters){
				partSum=0;// begin to compare nodes from cluster to cluster
				if(element1.get(0)==element2.get(0)){// if two same clusters are compared
					for(int i=0;i<element1.size();i++){
						for(int j=0;j<element2.size();j++){
							partSum+=(1-originalMatrix[(int)element1.get(i)][(int)element2.get(j)]);//prob between noded in the same cluster with same centers
						}//end of fourth for
					}//end of third for
					partSum=partSum/2;
				}//end of if
				else{
					for(int i=0;i<element1.size();i++){
						for(int j=0;j<element2.size();j++){
							partSum+=originalMatrix[(int)element1.get(i)][(int)element2.get(j)];//prob between noded in the same cluster with same centers
						}//end of fourth for
					}//end of third for
					partSum=partSum/2;
				}//end of else
				sum+=partSum;
			}//end of second for
		}//end of first for
		return sum;
	}//end of editDistance method

}
