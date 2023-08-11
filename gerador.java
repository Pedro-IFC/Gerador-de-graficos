import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class gerador {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Escreva a quantidade de números a ser gerado: ");
		int quantidade = input.nextInt();
		int min = 0;
		int max = 0;
		do {
			System.out.print("Escreva o valor minimo dos números: ");
			min = input.nextInt();
			System.out.print("Escreva o valor máximo dos números: ");
			max = input.nextInt();
		}while(min > max);
		
		int[] valores = generateRandom(quantidade, max, min);
		generateHtml(generateContent(valores));
		
		input.close();
	}
	public static int[] generateRandom(int qt,int max,int min) {
		int[] retorno = new int[qt]; 
		Random random = new Random();
		for(int x =0; x<qt; x++) {
			retorno[x] =  random.nextInt(max);
			while(retorno[x]>max || retorno[x]<min) {
				retorno[x] =  random.nextInt(max);
			}
		}
		return retorno;
	}
	public static String generateContent(int[] valores) {
		String conteudo = " <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n"
				+ "  <script type=\"text/javascript\">\r\n"
				+ "      google.charts.load('current', {packages: ['corechart', 'line']});\r\n"
				+ "google.charts.setOnLoadCallback(drawBasic);\r\n"
				+ "\r\n"
				+ "function drawBasic() {\r\n"
				+ "\r\n"
				+ "      var data = new google.visualization.DataTable();\r\n"
				+ "      data.addColumn('number', 'X');\r\n"
				+ "      data.addColumn('number', 'Valores');\r\n"
				+ "\r\n"
				+ "      data.addRows([";
		for(int x = 0; x<valores.length; x++){
			conteudo+="["+(int)(x+1)+","+ valores[x] +"]" +(x+1==valores.length? "" :", ");
		}
		conteudo+= "  ]);\r\n"
				+ "\r\n"
				+ "      var options = {\r\n"
				+ "        hAxis: {\r\n"
				+ "          title: 'Amostragem'\r\n"
				+ "        },\r\n"
				+ "        vAxis: {\r\n"
				+ "          title: 'Valores'\r\n"
				+ "        }\r\n"
				+ "      };\r\n"
				+ "\r\n"
				+ "      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));\r\n"
				+ "\r\n"
				+ "      chart.draw(data, options);\r\n"
				+ "    }\r\n"
				+ "  </script>\r\n"
				+ "<div id=\"chart_div\"></div>";
		return conteudo;
	}

	public static void generateHtml(String content) {
		try {
			FileWriter writer = new FileWriter("grafico.html");
			writer.write(content);
			writer.close();
			System.out.print("Arquivo gerado com sucesso");
		}catch(IOException e) {
			System.out.print("Ocorreu um erro");
		}
	}
}
