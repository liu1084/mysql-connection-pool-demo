/**
 * Created by jim on 2017/7/12.
 * This class is ...
 */

package com.jim.block;

public class StaticBlock {
	public static void main(String[] args) {
		new Child();
	}
}

class Parent {
	static String name = "hello";

	static {
		System.out.println("parent static block");
	}

	{
		System.out.println("parent block");
	}

	public Parent(String name) {
		System.out.println("parent constructor");
	}
}

class Child extends Parent {
	static String childName = "hello";

	static {
		System.out.println("child static block");
	}

	{
		System.out.println("child block");
	}

	public Child() {
		super("child2parent");
		System.out.println("child constructor");
	}
}

