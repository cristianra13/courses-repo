package app.test2;

public class D implements B, C 
{
	public static void main(String...a)
	{
		new D();
	}
	
	public D()
	{
		m();
	}

	@Override
	public void m() {
		// TODO Auto-generated method stub
		C.super.m();
	}
	
	
//	public void m()
//	{
//		B.super.m();
//	}
}
