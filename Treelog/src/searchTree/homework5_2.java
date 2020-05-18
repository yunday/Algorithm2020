package searchTree;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

class Weblog{
	String Ip;
	String Time;
	String Url;
	int Status;

	public Weblog(String ip, String time, String url, int status) {
		Ip=ip;
		Time=time;
		Url=url;
		Status=status;
	}
	public String getIp(){
		return Ip;
	}
	public String getTime(){
		return Time;
	}
	public String getUrl(){
		return Url;
	}
	public int getStatus(){
		return Status;
	}
}

class Timeset{
	String day;
	String month;
	String year;
	public Timeset(String d, String m, String y){
		day=d;
		month=m;
		year=y;
	}
	public String getday(){
		return day;
	}
	public String getmonth(){
		return month;
	}
	public String getyear(){
		return year;
	}
}

public class homework5_2 {
	public static int now=0;

	public static void main(String[] args) {
		ArrayList<Weblog> weblog=new ArrayList<>();
		while(true) {
            if(inputset(weblog)==1)
                break;
		}
	}

	public static int inputset(ArrayList<Weblog> weblog) {
		Scanner sc= new Scanner(System.in);
		String[] input_set=new String[2];
		int i=0;
		System.out.print("$ ");
		String input=sc.nextLine();

		StringTokenizer token=new StringTokenizer(input, " ");
		while(token.hasMoreTokens()) {
			input_set[i++]=token.nextToken();
		}

		switch(input_set[0]) {
		case "read":
			reading(input_set[1], weblog);
			return 0;
		case "sort":
			sorting(input_set[1], weblog);
			return 0;
		case "print":
			printing(weblog);
			return 0;
		case "exit":
			return 1;
		}
		return 0;
	}

	public static void reading(String input, ArrayList<Weblog> weblog) {
		try {
			ArrayList<String> fileset=new ArrayList<>();
			String path=homework5_2.class.getResource("").getPath();
			File file = new File(path+ input);
			BufferedReader bufReader=new BufferedReader(new FileReader(file));
			String line="";
			while((line=bufReader.readLine())!=null) {
				fileset.add(line);
			}
			bufReader.close();
			new_instance(fileset, weblog);

		}catch(FileNotFoundException e) {

		}catch(IOException e) {
			System.out.println(e);
		}
	}

	public static void sorting(String input, ArrayList<Weblog> weblog) {
		switch(input) {
		case "-t":
			now=1;
			sorting_t(weblog);
			break;
		case "-ip":
			now=2;
			sorting_ip(weblog);
			break;
		}
	}

	public static int ShowMonth(String str){
		switch(str) {
			case "Jan": return 1;
			case "Feb": return 2;
			case "Mar": return 3;
			case "Apr": return 4;
			case "May": return 5;
			case "Jun": return 6;
			case "Jul": return 7;
			case "Aug": return 8;
			case "Sep": return 9;
			case "Oct": return 10;
			case "Nov": return 11;
			case "Dec": return 12;
		}
		return 0;
	}

	public static int sub(Weblog w1, Weblog w2){
		StringTokenizer token1=new StringTokenizer(w1.getTime(), "/:");
		Timeset t1= new Timeset(token1.nextToken(), token1.nextToken(), token1.nextToken());
		StringTokenizer token2=new StringTokenizer(w2.getTime(), "/:");
		Timeset t2= new Timeset(token2.nextToken(), token2.nextToken(), token2.nextToken());

		if(t1.getyear().compareTo(t2.getyear())>0)
			return 1;
		else if(t1.getyear().compareTo(t2.getyear())<0)
			return -1;
		if(ShowMonth(t1.getmonth())<ShowMonth(t2.getmonth()))
			return 1;
		else if(ShowMonth(t1.getmonth())>ShowMonth(t2.getmonth()))
			return -1;
		if(t1.getday().compareTo(t2.getday())>0)
			return 1;
		else if(t1.getday().compareTo(t2.getday())<0)
			return -1;
		String a=token1.nextToken("/");
		String b=token2.nextToken("/");
		if(a.compareTo(b)>0)
			return 1;
		else if(a.compareTo(b)<0)
			return -1;
		return 0;
	}

	public static void sorting_t(ArrayList<Weblog> weblog){
		Collections.sort(weblog, new Comparator<Weblog>(){
			@Override
			public int compare(Weblog w1, Weblog w2){
				return sub(w1, w2);
			}
		});
	}

	public static void sorting_ip(ArrayList<Weblog> weblog){
		Collections.sort(weblog, new Comparator<Weblog>(){
			@Override
			public int compare(Weblog w1, Weblog w2) {
				if(w1.getIp().compareTo(w2.getIp())>0)
					return 1;
				else if(w1.getIp().compareTo(w2.getIp())<0)
					return -1;
				return sub(w1, w2);
			}
		});
	}

	public static void printing(ArrayList<Weblog> weblog) {
		if(now==1){
			for(int i=0;i<weblog.size();i++){
				System.out.println(weblog.get(i).getTime());
				System.out.println("\tIP: "+ weblog.get(i).getIp());
				System.out.println("\tURL: "+ weblog.get(i).getUrl());
				System.out.println("\tStatus: "+ weblog.get(i).getStatus());
			}
		}
		else{
			for(int i=0;i<weblog.size();i++){
				System.out.println(weblog.get(i).getIp());
				System.out.println("\tTime: "+ weblog.get(i).getTime());
				System.out.println("\tURL: "+ weblog.get(i).getUrl());
				System.out.println("\tStatus: "+ weblog.get(i).getStatus());
			}
		}
	}

	public static void new_instance(ArrayList<String> fileset,ArrayList<Weblog> weblog) {
		for(int i=1;i<fileset.size();i++){
			StringTokenizer token=new StringTokenizer(fileset.get(i), ",[");
			weblog.add(new Weblog(token.nextToken(), token.nextToken(), token.nextToken(), Integer.parseInt(token.nextToken())));
		}
	}

}