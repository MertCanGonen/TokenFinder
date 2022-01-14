import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class hw1_MertCan_Gonen {
	
	static ArrayList<String> letters = new ArrayList<>();
	static ArrayList<String> numbers = new ArrayList<>();
	static ArrayList<String> characters = new ArrayList<>();
	static ArrayList<String> resWords = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Scanner s = null;
		String file = args[0];
		String result = args[1];
		ArrayList<String> last = new ArrayList<>();
		ArrayList<String> tm = new ArrayList<>();
		ArrayList<String> words = new ArrayList<>();
		ArrayList<String> words2 = new ArrayList<>();
		ArrayList<String> words3 = new ArrayList<>();
		
		
		letters.add("a");letters.add("b");letters.add("c");letters.add("d");letters.add("e");letters.add("f");letters.add("g");letters.add("h");
		letters.add("i");letters.add("j");letters.add("k");letters.add("l");letters.add("m");letters.add("n");letters.add("o");letters.add("p");
		letters.add("r");letters.add("q");letters.add("s");letters.add("t");letters.add("u");letters.add("v");letters.add("w");letters.add("x");
		letters.add("y");letters.add("z");letters.add("A");letters.add("B");letters.add("C");letters.add("D");letters.add("E");letters.add("F");
		letters.add("G");letters.add("H");letters.add("I");letters.add("J");letters.add("K");letters.add("L");letters.add("M");letters.add("N");
		letters.add("O");letters.add("P");letters.add("R");letters.add("Q");letters.add("S");letters.add("T");letters.add("U");letters.add("V");
		letters.add("W");letters.add("X");letters.add("Y");letters.add("Z");numbers.add("0");numbers.add("1");numbers.add("2");numbers.add("3");
		numbers.add("4");numbers.add("5");numbers.add("6");numbers.add("7");numbers.add("8");numbers.add("9");characters.add("(");characters.add(")");
		characters.add(":=");characters.add("+");characters.add("-");characters.add("/");characters.add("*");characters.add(";");characters.add("<>");characters.add(">");
		characters.add("<");characters.add(">=");characters.add("<=");characters.add(",");characters.add(":");
		resWords.add("begin");resWords.add("end");resWords.add("if");resWords.add("then");resWords.add("else");resWords.add("while");resWords.add("program");
		resWords.add("integer");resWords.add("var");
		
		
		boolean comment = false;
		try {
			s = new Scanner(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (s.hasNext()) {     
			tm.add(s.next());
		}
		
		for (int i=0;i<tm.size();i++) {  
			String tmp = tm.get(i);
			if (tmp.indexOf("(") != -1) {
				tmp = tmp.replace("(", " ( ");
			}if (tmp.indexOf(")") != -1) {
				tmp = tmp.replace(")", " ) ");
			}if (tmp.indexOf(":=") != -1) {
				tmp = tmp.replace(":=", " := ");
			}if (tmp.indexOf("+") != -1) {
				tmp = tmp.replace("+", " + ");
			}if (tmp.indexOf("-") != -1) {
				tmp = tmp.replace("-", " - ");
			}if (tmp.indexOf("/") != -1) {
				tmp = tmp.replace("/", " / ");
			}if (tmp.indexOf("*") != -1) {
				tmp = tmp.replace("*", " * ");
			}if (tmp.indexOf(";") != -1) {
				tmp = tmp.replace(";", " ; ");
			}if (tmp.indexOf("<>") != -1) {
				tmp = tmp.replace("<>", " <> ");
			}if (tmp.indexOf(">") != -1) {
				if (tmp.indexOf(">=") == -1 && tmp.indexOf("<>") == -1) {
					tmp = tmp.replace(">", " > ");
				}
			}if (tmp.indexOf("<") != -1) {
				if (tmp.indexOf("<=") == -1 && tmp.indexOf("<>") == -1) {
					tmp = tmp.replace("<", " < ");
				}
			}if (tmp.indexOf(">=") != -1) {
				tmp = tmp.replace(">=", " >= ");
			}if (tmp.indexOf("<=") != -1) {
				tmp = tmp.replace("<=", " <= ");
			}if (tmp.indexOf(",") != -1) {
				tmp = tmp.replace(",", " , ");
			}if (tmp.indexOf(":") != -1) {
				if (tmp.indexOf(":=") == -1) {
					tmp = tmp.replace(":", " : ");
				}
			}if (tmp.indexOf("%") != -1) {
				tmp = tmp.replace("%", " % ");
			}
			words.add(tmp);
		}
		
		for (int i=0; i<words.size(); i++) {   
			if (words.get(i).charAt(0) == ' ') {
				for (int j=0; j<words.get(i).length(); j++) {
					if (words.get(i).charAt(j) != ' ') {
						words2.add(words.get(i).substring(j));
						break;
					}
				}
			}else {
				words2.add(words.get(i));
			}
		}
		
		for (int i=0;i<words2.size();i++) {   
			int start = 0;
			String tmp = words2.get(i);
			while (tmp.length() != 0) {
				if (tmp.indexOf(" ") == -1) {
					words3.add(tmp);
					break;
				}else {
					int idx = tmp.indexOf(" ");
					words3.add(tmp.substring(0,idx));
					if (idx == tmp.length()-1) {
						tmp = "";
					}else {
						tmp = tmp.substring(idx+1);
					}
				}
			}
		}
		
		for (int i = 0; i < words3.size(); i++) {       
			if (words3.get(i).indexOf("%") != -1 && comment == false) {
				comment = true;
			}else if (words3.get(i).indexOf("%") != -1 && comment == true) {
				comment = false;
			}else if (comment == false){
				if (words3.get(i).length() > 0) {
					last.add(words3.get(i));
				}
			}
		}
		
		PrintWriter p = null;
		try {
			p = new PrintWriter(new FileOutputStream(result));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i=0;i<last.size();i++) {
			if (last.get(i).equals("(")) {  
				p.println("Next token is LPARANT   	Next lexeme is (");
			}else if (last.get(i).equals(")")) {
				p.println("Next token is RPARANT	    Next lexeme is )");
			}else if (last.get(i).equals(":=")) {
				p.println("Next token is ASSIGNM	    Next lexeme is :=");
			}else if (last.get(i).equals("+")) {
				p.println("Next token is ADD	        Next lexeme is +");
			}else if (last.get(i).equals("-")) {
				p.println("Next token is SUBT	        Next lexeme is -");
			}else if (last.get(i).equals("/")) {
				p.println("Next token is DIV	        Next lexeme is /");
			}else if (last.get(i).equals("*")) {
				p.println("Next token is MULT	        Next lexeme is *");
			}else if (last.get(i).equals(";")) {
				p.println("Next token is SEMICOLON	    Next lexeme is ;");
			}else if (last.get(i).equals("<>")) {
				p.println("Next token is NOTEQ	       Next lexeme is <>");
			}else if (last.get(i).equals(">")) {
				p.println("Next token is GREATER	    Next lexeme is >");
			}else if (last.get(i).equals("<")) {
				p.println("Next token is LESS	        Next lexeme is <");
			}else if (last.get(i).equals(">=")) {
				p.println("Next token is GRE_EQ	    Next lexeme is >=");
			}else if (last.get(i).equals("<=")) {
				p.println("Next token is LESS_EQ	   Next lexeme is <=");
			}else if (last.get(i).equals(",")) {
				p.println("Next token is COMMA	        Next lexeme is ,");
			}else if (last.get(i).equals(":")) {
				p.println("Next token is COLON	        Next lexeme is :");
			}
			else {
				boolean checkUnknown = checkUnknownSign(last.get(i));
				if (checkUnknown) {
					p.println("Next token is UNKNOWN	  Next lexeme is "+last.get(i));
				}else {
					boolean checkIntorWord = checkIntorWord(last.get(i));
					if (checkIntorWord) {
						if (resWords.contains(last.get(i).toLowerCase())) {
							if (last.get(i).equalsIgnoreCase("begin")) {
								p.println("Next token is RES_WORD	    Next lexeme is "+last.get(i));
							}else if (last.get(i).equalsIgnoreCase("end")) {
								p.println("Next token is RES_WORD	    Next lexeme is "+last.get(i));
							}else if (last.get(i).equalsIgnoreCase("if")) {
								p.println("Next token is RES_WORD	    Next lexeme is "+last.get(i));
							}else if (last.get(i).equalsIgnoreCase("then")) {
								p.println("Next token is RES_WORD	    Next lexeme is "+last.get(i));
							}else if (last.get(i).equalsIgnoreCase("else")) {
								p.println("Next token is RES_WORD	    Next lexeme is "+last.get(i));
							}else if (last.get(i).equalsIgnoreCase("while")) {
								p.println("Next token is RES_WORD	    Next lexeme is "+last.get(i));
							}else if (last.get(i).equalsIgnoreCase("program")) {
								p.println("Next token is RES_WORD	    Next lexeme is "+last.get(i));
							}else if (last.get(i).equalsIgnoreCase("integer")) {
								p.println("Next token is RES_WORD	    Next lexeme is "+last.get(i));
							}else if (last.get(i).equalsIgnoreCase("var")) {
								p.println("Next token is RES_WORD	    Next lexeme is "+last.get(i));
							}
						}else {
							if (last.get(i).length() > 15 || !letters.contains(last.get(i).charAt(0)+"")) { 
								p.println("Next token is UNKNOWN   	Next lexeme is "+last.get(i));
							}else {
								p.println("Next token is identifier    Next lexeme is "+last.get(i));
							}
						}
					}else { 
						p.println("Next token is INT_LIT       Next lexeme is "+last.get(i));
					}	
				}
			}
		}
		p.print("Next token is EOF           Next lexeme is end of file.");
		p.close();
	}
	
	public static boolean checkUnknownSign(String w) {  
		boolean check= false;
		for (int i = 0; i < w.length(); i++) {  
			if (!letters.contains(w.charAt(i)+"") && !numbers.contains(w.charAt(i)+"") && characters.contains(w.charAt(i)+"")) {
				check = true;
				return check;
			}
		}
		return check;
	}
	
	public static boolean checkIntorWord(String w) {   
		for (int i=0;i<w.length();i++) {
			if (letters.contains(w.charAt(i)+"")) {
				return true;
			}
		}
		return false;
	}
		
}


