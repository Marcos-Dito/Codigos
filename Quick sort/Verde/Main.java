import java.io.*;
import java.nio.charset.*;

public class Main {

  static int pesquisaSerie(String linha, Serie[] serie){
 
      for(int i = 0; i < serie.length; i++) {
  			
  			if(serie[i].getNome().equals(linha)) {
          
  				return i;

  			}
  			
  		}
      return -1;
  }

  
  public static void main(String[] args) {

    MyIO.setCharset("UTF-8");
    int i = 0, j = -2, cont = -1;
    
    ArquivoTextoLeitura input = new ArquivoTextoLeitura("//tmp/data.txt");
    ArquivoTextoEscrita output = new ArquivoTextoEscrita("//tmp/666578_bolha.txt");

    String linha = input.ler(); String wire, fio;
    
    while(linha != null) {
    	
    	linha = input.ler();
    	cont++;
      
    }
    
    Serie[] b = new Serie[cont];
    
    input = new ArquivoTextoLeitura("//tmp/data.txt");
	  wire = input.ler();

  	for (i = 0; i < cont; i++) {
  	
      wire = input.ler();
        
    	b[i] = new Serie();
      
    	b[i].ler(wire);
    
  	}

    input.fecharArquivo();

  	int quantidade =  MyIO.readInt();
  	int posição = 0;
  	
  	Serie[] c = new Serie[quantidade];
  	
  	for(i = 0; i < quantidade; i++) {
  		
  		fio = MyIO.readLine();
      
      posição = pesquisaSerie(fio, b);
      
      c[i] = b[posição].clone();		
  		
  	}

    Quicksort quick = new Quicksort();
    quick.quicksort(c, quantidade);

    for(j = 0; j < c.length; j++){

      c[j].imprimir();
  
    }
    
    output.escrever("746639" + "\t" + quick.result + "\t" + quick.COMPARACOES_ENTRE_ELEMENTOS + "\t" + quick.MOVIMENTACOES_ENTRE_ELEMENTOS);
    output.fecharArquivo();

  }

}

class Quicksort {

  public int COMPARACOES_ENTRE_ELEMENTOS = 0, MOVIMENTACOES_ENTRE_ELEMENTOS = 0;

  public long tempoInicial;

  public void quicksort(Serie[] array, int n) {
    tempoInicial = System.currentTimeMillis();
  	sort(array, 0, n - 1);
  }
  
  /**
  * Algoritmo de ordenação Quicksort.
  * @param int esq: início da partição a ser ordenada
  * @param int dir: fim da partição a ser ordenada
  */
  public void sort(Serie[] array, int esq, int dir) {
  			
  	int part;
  	if (esq < dir){
      COMPARACOES_ENTRE_ELEMENTOS++;
  		part = particao(array, esq, dir);
  		sort(array, esq, part - 1);
  		sort(array, part + 1, dir);
  	}
  }
  				
  public int particao(Serie[] array, int inicio, int fim) {
  		
  	Serie pivot = array[fim];
  	int part = inicio - 1;
  	for (int i = inicio; i < fim; i++) {
  		if (array[i].getNumepis() < pivot.getNumepis() || (array[i].getNumepis() == pivot.getNumepis()) && 
         (array[i].getNome().compareTo(pivot.getNome()) < 0)) {
  			part++;
  			swap(array, part, i);
  		}
  	}
  	part++;
  	swap(array, part, fim);
  	return (part);
  }
  	
  public void swap(Serie[] array, int i, int j) {
  	MOVIMENTACOES_ENTRE_ELEMENTOS++;      
  	Serie temp = array[i];
  	array[i] = array[j];
  	array[j] = temp;
  }  

  public double result = ((System.currentTimeMillis() - tempoInicial));
  
}

class ArquivoTextoLeitura {

	private BufferedReader entrada;
	
	ArquivoTextoLeitura(String nomeArquivo) {	
		
		try {
			entrada = new BufferedReader(new FileReader(nomeArquivo));
		}
		catch (FileNotFoundException excecao) {
			System.out.println("Arquivo nao encontrado");
		}
	}
	
	public void fecharArquivo() {
		
		try {
			entrada.close();
		}
		catch (IOException excecao) {
			System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);	
		}
	}
	
	@SuppressWarnings("finally")
	public String ler() {
		
		String textoEntrada = null;
		
		try {
			textoEntrada = entrada.readLine();
		}
		catch (EOFException excecao) { //Excecao de final de arquivo.
			textoEntrada = null;
		}
		catch (IOException excecao) {
			System.out.println("Erro de leitura: " + excecao);
			textoEntrada = null;
		}
		finally {
			return textoEntrada;
		}
	}
}

class ArquivoTextoEscrita {

	private BufferedWriter saida;
		
	ArquivoTextoEscrita(String nomeArquivo) {	
		
		try {
			saida = new BufferedWriter(new FileWriter(nomeArquivo));
		}
		catch (FileNotFoundException excecao) {
			System.out.println("Arquivo nao encontrado");
		}
		catch (IOException excecao) {
			System.out.println("Erro na abertura do arquivo de escrita: " + excecao);
		}
	}
	
	public void fecharArquivo() {
		
		try {
			saida.close();
		}
		catch (IOException excecao) {
			System.out.println("Erro no fechamento do arquivo de escrita: " + excecao);	
		}
	}
	
	public void escrever(String textoEntrada) {
	
		try {
			saida.write(textoEntrada);
			saida.newLine();
		}
		catch (IOException excecao){
			System.out.println("Erro de entrada/saída " + excecao);
		}
	}
}


class MyIO {

   private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
   private static String charset = "ISO-8859-1";

   public static void setCharset(String charset_){
      charset = charset_;
      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
   }

   public static void print(){
   }

   public static void print(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(){
   }

   public static void println(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void printf(String formato, double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.printf(formato, x);// "%.2f"
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static double readDouble(){
      double d = -1;
      try{
         d = Double.parseDouble(readString().trim().replace(",","."));
      }catch(Exception e){}
      return d;
   }

   public static double readDouble(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readDouble();
   }

   public static float readFloat(){
      return (float) readDouble();
   }

   public static float readFloat(String str){
      return (float) readDouble(str);
   }

   public static int readInt(){
      int i = -1;
      try{
         i = Integer.parseInt(readString().trim());
      }catch(Exception e){}
      return i;
   }

   public static int readInt(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readInt();
   }

   public static String readString(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != ' ' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n' && tmp != ' ');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readString(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readString();
   }

   public static String readLine(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readLine(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readLine();
   }

   public static char readChar(){
      char resp = ' ';
      try{
         resp  = (char)in.read();
      }catch(Exception e){}
      return resp;
   }

   public static char readChar(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readChar();
   }

   public static boolean readBoolean(){
      boolean resp = false;
      String str = "";

      try{
         str = readString();
      }catch(Exception e){}

      if(str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") || 
            str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")){
         resp = true;
            }

      return resp;
   }

   public static boolean readBoolean(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readBoolean();
   }

   public static void pause(){
      try{
         in.read();
      }catch(Exception e){}
   }

   public static void pause(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      pause();
   }
}

class Serie{

  private String nome;
  private String formato;
  private String duração;
  private String paísOrig;
  private String idiomOrig;
  private String emissora;
  private String iniciotransm;
  private int temps;
  private int numepis;

  public Serie(String nome, String formato, String duração, String paísOrig, String idiomOrig, String emissora, String iniciotransm, int temps, int numepis){

    this.nome = nome;
    this.formato = formato;
    this.duração = duração;
    this.paísOrig = paísOrig;
    this.idiomOrig = idiomOrig;    
    this.emissora = emissora;
    this.iniciotransm = iniciotransm;
    this.temps = temps;
    this.numepis = numepis;
    
  }

  public Serie(){
    
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getFormato() {
    return formato;
  }

  public void setFormato(String formato) {
    this.formato = formato;
  }

  public String getDuração() {
    return duração;
  }

  public void setDuração(String duração) {
    this.duração = duração;
  }

  public String getPaísOrig() {
    return paísOrig;
  }

  public void setPaísOrig(String paísOrig) {
    this.paísOrig = paísOrig;
  }

  public String getIdiomOrig() {
    return idiomOrig;
  }

  public void setIdiomOrig(String idiomOrig) {
    this.idiomOrig = idiomOrig;
  }

  public String getEmissora() {
    return emissora;
  }

  public void setEmissora(String emissora) {
    this.emissora = emissora;
  }

  public String getIniciotransm() {
    return iniciotransm;
  }

  public void setIniciotransm(String iniciotransm) {
    this.iniciotransm = iniciotransm;
  }

  public int getTemps() {
    return temps;
  }

  public void setTemps(int temps) {
    this.temps = temps;
  }

  public int getNumepis() {
    return numepis;
  }

  public void setNumepis(int numepis) {
    this.numepis = numepis;
  }

  public void ler(){

    ArquivoTextoLeitura input = new ArquivoTextoLeitura("//tmp/data.txt");
    String X = input.ler();
    
    String[] splitX = X.split(";"); 
    setNome(splitX[0]);
    setFormato(splitX[1]);
    setDuração(splitX[2]);    
    setPaísOrig(splitX[3]); 
    setIdiomOrig(splitX[4]); 
    setEmissora(splitX[5]); 
    setIniciotransm(splitX[6]);
    setTemps(Integer.parseInt(splitX[7]));
    setNumepis(Integer.parseInt(splitX[8]));
    
  }

  public void ler(String str){

	    
	    String[] splitX = str.split(";"); 
	    setNome(splitX[0]);
	    setFormato(splitX[1]);
	    setDuração(splitX[2]);    
	    setPaísOrig(splitX[3]); 
	    setIdiomOrig(splitX[4]); 
	    setEmissora(splitX[5]); 
	    setIniciotransm(splitX[6]);
	    setTemps(Integer.parseInt(splitX[7]));
	    setNumepis(Integer.parseInt(splitX[8]));
	    
	    
	  }

  public void imprimir(){

    
    MyIO.println( this.getNome() + " ## " + this.getFormato() + " ## " + this.getDuração() + " ## " + this.getPaísOrig() + " ## " + this.getIdiomOrig() + " ## " + this.getEmissora() + " ## " + this.getIniciotransm() + " ## " + this.getTemps() + " ## " + this.getNumepis() );
    
    
    
  }

  public Serie clone(){
    Serie serie = new Serie();
    serie.setNome(this.nome);
    serie.setFormato(this.formato);
    serie.setDuração(this.duração);
    serie.setPaísOrig(this.paísOrig);
    serie.setIdiomOrig(this.idiomOrig);
    serie.setEmissora(this.emissora);   
    serie.setIniciotransm(this.iniciotransm);  
    serie.setTemps(this.temps);  
    serie.setNumepis(this.numepis); 
    return serie;
  }
  
}