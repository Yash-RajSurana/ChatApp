
class loop
{
	public static void main(String[] a)
	{
	int i,j,k=1;
	
	for(i=1;i<5;i++)
	{	k=i;
		
		for(j=1;j<=4;j++)
		{	if(k==5 ) k=1;
			System.out.print(k++%5);
			
		}
		System.out.println();
		
	}
	}	
}