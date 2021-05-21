package biblioteca;
//Editora se encontra dentro do pacote biblioteca
import java.util.ArrayList;
import java.io.*;
import java.io.File;
//importando as bibliotecas para poder utilizar ArrayList e Arquivo

public class Editora{

	private String Name,Address,ID;
	//Name = Nome da Editora ; Address = Endereço da Editora; ID = ID de cadastro da Editora

	public Editora(String nome, String end, String cod){
	//Criando e colocando os valores na classe
		this.Name=nome;
		this.Address=end;
		this.ID=cod;

	}

	//Set do Nome da editora
	public void setNome(String nome){

		this.Name=nome;

	}
	
	//Set do Endereço da editora
	public void setEnd(String end){

		this.Address=end;

	}

	//Set do ID da editora
	public void setID(String cod){

		this.ID=cod;

	}

	//Get do Nome da editora
	public String getNome(){

		return this.Name;

	}

	//Get do Endereço da editora
	public String getEnd(){

		return this.Address;

	}

	//Get do ID da editora
	public String getID(){

		return this.ID;

	}

	//Metodo para adicionar ao arquivo "Editoras.txt"
	public void adicionarArquivo(File arquivoe, Editora ee){

		try{
			//Cria o FileWriter e BufferedWriter para adicionar as informações no arquivo

			FileWriter fw = new FileWriter(arquivoe,true);
            		BufferedWriter bw = new BufferedWriter(fw);

			//A sequencia de dados será: Nome, Endereço e por ultimo o ID da editora

			bw.write(ee.getNome());
			bw.newLine();

			bw.write(ee.getEnd());
			bw.newLine();

			bw.write(ee.getID());
			bw.newLine();

			//Terminando de colocar todos os dados o FileWriter e BufferedWriter são fechados
			bw.close();
			fw.close();


		}

		catch(IOException e){
			System.out.println("Erro: "+e.toString());
		}


	}

	//Metodo para ler o Arquivo "Editoras.txt"
	public ArrayList<Editora> lerArquivo(File arquivoe, ArrayList<Editora> E){

				try{
				//Cria o FileReader e o BufferedReader para a leitura do arquivo
				FileReader fr = new FileReader(arquivoe);
            			BufferedReader br = new BufferedReader(fr);
				//Criando uma ArrayList String para pegar todas as linhas do arquivo
				ArrayList<String> S = new ArrayList<String>();

				//Vai repetir até que não tenha mais linhas no arquivo
				while (br.ready()){
				
					//Le a linha e adiciona na Lista 
                			String linha = br.readLine();
					S.add(linha);
				
            			}

				//Vai repetir até que z seja do mesmo tamanho da Lista
				for(int z=0;z<S.size();z++){

					//Cria uma nova varíavel do tipo Editora
					Editora ee = new Editora("","","");
			
					//Vai pegar o que está na mesma posição de S
					//A sequencia vai ser sempre Nome,Endereço e por ultimo ID
					ee.setNome(S.get(z));
					z++;

					ee.setEnd(S.get(z));
					z++;

					ee.setID(S.get(z));

					//Depois de pegar as 3 informações ee é adicionado na lista de Editoras E .

					E.add(ee);

				}

				//Depois de todas as Editoras terem sido colocadas na lista o FileReader e o BufferedReader são fechados
				br.close();
				fr.close();

				

				
		}

		

		catch(IOException e){
			System.out.println("Erro: "+e.toString());
		}

		//O metodo depois de tudo retorna a lista de editoras E.
		return E;

	}

	}




