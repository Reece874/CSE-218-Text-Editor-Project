package model;

import java.util.LinkedList;

public class Link implements Comparable<Link>{
	
	private String str; 
	private LinkedList<Link> babyList; 
	
	public Link(String str) {
		this.str = str; 
		babyList = new LinkedList<>();
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public LinkedList<Link> getBabyList() {
		return babyList;
	}

	public void setBabyList(LinkedList<Link> babyList) {
		this.babyList = babyList;
	}
	
	public void insertBabyList(Link link) {
		babyList.add(link);
	}

	public String toString() {
		return str; 
	}
	
	@Override
	public int compareTo(Link o) {
		return this.getStr().compareTo(o.getStr());
	}
	
	

}
