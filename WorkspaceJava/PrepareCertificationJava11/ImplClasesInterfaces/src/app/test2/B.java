package app.test2;

public interface B {

	default void m() {
		System.out.println("int B");
	}
}
