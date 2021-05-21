package biblioteca;
//Livro se encontra dentro do pacote biblioteca
import java.util.ArrayList;
import java.io.*;
import java.io.File;
//importando as bibliotecas para poder utilizar ArrayList e Arquivo

public class Livro{

private String Title,Author,ID;
private Editora Publisher;

//Title = Titulo do Livro, Author = Nome do autor do Livro, ID = ID de cadastro dos Livros, Publisher = Editora que o livro foi publicado 

	public Livro(String titulo, String autor, String cod, Editora ed){
		//Criando e colocando os valores na classe
		this.Title=titulo;
		this.Author=autor;
		this.ID=cod;
		this.Publisher=ed;

	}

	//Set do Titulo do Livro
	public void setTitulo(String titulo){

		this.Title=titulo;

	}

	//Set do Nome do Autor do Livro
	public void setAutor(String autor){

		this.Author=autor;

	}

	//Set do ID do Livro
	public void setID(String cod){

		this.ID=cod;

	}

	//Set da Editora que o Livro Pertence
	public void setEditora(Editora ed){

		this.Publisher=ed;

	}

	//Get do Titulo do Livro
	public String getTitulo(){

		return this.Title;

	}

	//Get do Nome do Autor que escreveu o Livro
	public String getAutor(){

		return this.Author;

	}

	//Get do ID do Livro
	public String getID(){

		return this.ID;

	}

	//Get da Editora que publicou o livro
	public Editora getEditora(){

		return this.Publisher;

	}


	//Metodo para adicionar as informações no arquivo "Livro.txt"
	public void adicionarArquivo(File arquivol, Livro ll){

		try{

			//Cria o FileWriter e BufferedWriter para adicionar as informações no arquivo
			FileWriter fw = new FileWriter(arquivol,true);
            		BufferedWriter bw = new BufferedWriter(fw);

			//A sequencia de dados será: Titulo, Autor, ID da Editora(Só precisa do ID, pois no metodo de leitura de arquivo ele busca a Editora na Lista de Editoras por meio de seu ID, e por ultimo o ID do Livro
			bw.write(ll.getTitulo());
			bw.newLine();

			bw.write(ll.getAutor());
			bw.newLine();

			bw.write(ll.getEditora().getID());
			bw.newLine();

			bw.write(ll.getID());
			bw.newLine();

			//Terminando de colocar todos os dados o FileWriter e BufferedWriter são fechados
			bw.close();
			fw.close();
		}

		catch(IOException e){
			System.out.println("Erro: "+e.toString());
		}


	}

	//Metodo para ler o Arquivo "Livros.txt"
	public ArrayList<Livro> lerArquivo(File arquivol, ArrayList<Livro> L,ArrayList<Editora> E){

			try{
				//Cria o FileReader e o BufferedReader para a leitura do arquivo
				FileReader fr = new FileReader(arquivol);
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

					//Cria duas novas varíaveis uma do tipo Editora e outra do tipo Livro
					Editora ee = new Editora("","","");
					Livro ll = new Livro("","","",ee);

					//Vai pegar o que está na mesma posição de S
					//A sequencia vai ser sempre Titulo,Autor, ID da Editora e por ultimo ID do Livro
					ll.setTitulo(S.get(z));
					z++;

					ll.setAutor(S.get(z));
					z++;
					//Esse for percorre toda a lista de Editoras cadastradas para a achar a Editora que possui o ID correspondente
					for(int y=0;y<E.size();y++){
						//Encontra a Editora que possui o mesmo ID que está no arquivo
						if(S.get(z).equals(E.get(y).getID())){

							ll.setEditora(E.get(y));
						}
					}

					z++;

					ll.setID(S.get(z));


					//Depois de todas as informações ll é adicionado na lista de Livros L 
					L.add(ll);

				}

				//Depois de todas os Livros terem sido colocadas na lista o FileReader e o BufferedReader são fechados
				br.close();
				fr.close();

		}


		catch(IOException e){
			System.out.println("Erro: "+e.toString());
		}

		//O metodo depois de tudo retorna a lista de livros L.
		return L;

	}

}
