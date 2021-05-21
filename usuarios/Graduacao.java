//Graduacao pertence ao pacote usuarios
package usuarios;
//Importa todo o pacote biblioteca(Livro e Editora)
import biblioteca.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.File;
//importando as bibliotecas para poder utilizar ArrayList e Arquivo

//Classe Graduacao extende Pessoa, ou seja, pega metodos e atributos que ja existem em Pessoa
public class Graduacao extends Pessoa{

	private String ra;
	//RA = Código de identificação do Aluno de Graduação

	public Graduacao(String name,int age,int quantity,String Email,ArrayList<Livro> books,String id){

		//Essas variaveis dentro do parenteses de super são as variaveis que são originais de Pessoa e que Graduacao tambem possui
		super(name,age,quantity,Email,books);
		//Nome,idade,quantidade de livros reservados,e-mail e lista de livros reservados respectivamente
		this.ra=id;
	}

	//Set do RA da Pessoa
	public void setRA(String cod){

		this.ra=cod;
	}

	//Get do RA da Pessoa
	public String getRA(){

		return this.ra;
	}

	//Metodo para printar as informações das pessoas de Graduação que estão na Lista
	public void Printar(ArrayList<Graduacao> G){

		//Percorre a Lista toda
		for(int z=0;z<G.size();z++){
			//Se a pessoa não tiver nenhum livro reservado é printado essa mensagem
			if(G.get(z).getQuant()==0){
				System.out.println("\nNome: "+G.get(z).getNome()+"\nIdade: "+G.get(z).getIdade()+"\nEmail: "+G.get(z).getEmail()+"\nID: "+G.get(z).getRA());
			}

			//Já se a pessoa tiver pelo menos um livro reservado essa mensagem é mostrada
			if(G.get(z).getQuant()!=0){

				System.out.println("\nNome: "+G.get(z).getNome()+"\nIdade: "+G.get(z).getIdade());
				System.out.println("Quantidade de livros reservados: "+G.get(z).getQuant()+"\n----LIVROS----");
				//Esse for percorre a Lista de livros reservados, que está dentro da classe
				for(int y=0;y<G.get(z).getLivros().size();y++){
					//Printa o Titulo do Livro
					System.out.println(G.get(z).getLivros().get(y).getTitulo());
				}
				System.out.println("--------------\nEmail: "+G.get(z).getEmail()+"\nID: "+G.get(z).getRA());
			}
		}
	}

	//Método para devolução de Livros
	//Esse metodo recebe a Pessoa da Graduação que tem algum livro reservado
	public ArrayList<Livro> Devolver(Graduacao P){

		//Essa variavel foi criado somente para printar ou não a mensagem de erro
		int resul=0;
		//Criação de um scanner
		Scanner ler = new Scanner(System.in);

		System.out.println("\nQual livro deseja devolver?");

		//Percorre a Lista de Livros reservados da pessoa
		for(int z=0;z<P.getLivros().size();z++){

				System.out.println("Titulo: "+P.getLivros().get(z).getTitulo());
				System.out.println("ID: "+P.getLivros().get(z).getID()+"\n");
		}
		//Usuário digita o ID do livro que deseja resolver
		System.out.println("\nDigite o ID:");
		String idd=ler.next();

		//Percorre novamente a lista de livros reservados da pessoa
		for(int z=0;z<P.getLivros().size();z++){

			//O ID digitado pelo usuário sendo igual a um dos ID's da lista, o programa então removera o livro dessa posição
			if(idd.equals(P.getLivros().get(z).getID())){
				resul=1;
				System.out.println("O livro "+P.getLivros().get(z).getTitulo()+" será devolvido!");
				P.getLivros().remove(z);
				break;
			}

		}

		//O livro não sendo encontrado na lista de livros reservados, o metodo mostrara essa mensagem de erro
		if(resul==0){

			System.out.println("\nNenhum livro foi encontrado!\n");
		}
		//Programa retorna a Lista de Livros Reservados da Pessoa
		return P.getLivros();
	}

	//Metodo para Reservar Livros
	//Recebe a Pessoa da Graduação e a Lista de Livros cadastrados
	public ArrayList<Livro> Reservar(Graduacao P,ArrayList<Livro> L ){
		
		//Essa variavel foi criado somente para printar ou não a mensagem de erro
		int resul=0;
		//Criação de um scanner
		Scanner ler = new Scanner(System.in);

		System.out.println("\nDigite o ID do livro:");
		String idd=ler.next();

		//Percorre a Lista de Livros cadastrados
		for(int z=0;z<L.size();z++){

			//O ID digitado do usuario sendo encontrado dentro da Lista, faz o livro ser adicionado na Lista de Livros Reservados da classe Graduação
			if(idd.equals(L.get(z).getID())){

				resul=1;

				P.getLivros().add(L.get(z));

				System.out.println("Livro reservado!\n");
			}
		}

		//Mensagem de erro se nenhum livro possui o ID digitado
		if(resul==0){

			System.out.println("\nNenhum livro foi encontrado!\n");
		}

		//Retorna a lista de livros reservados
		return P.getLivros();
	}

	//Metodo para escrever as informações no arquivo
	//Recebe o arquivo que vai ser escrito, a pessoa da graduação que vai ter as informações escritas
	//O filewriter e o bufferedwriter
	public void adicionarArquivo(File arquivop, Graduacao pp,FileWriter fw,BufferedWriter bw){

		try{
			//Vai ser escrito o Nome,Idade,E-mail,Quantidade de livros Reservados,os IDs dos livros reservados e por fim o ID da pessoa da graduação
			bw.write(pp.getNome());
			bw.newLine();

			String idd=Integer.toString(pp.getIdade());

			bw.write(idd);
			bw.newLine();

			bw.write(pp.getEmail());
			bw.newLine();

			idd=Integer.toString(pp.getQuant());

			bw.write(idd);
			bw.newLine();
			//Foi colocado a quantidade de livros porque quando o programa for ler o arquivo ele vai saber quantos livros serão, e quantas linhas são dos IDs do livro
			for(int z=0;z<pp.getLivros().size();z++){

				bw.write(pp.getLivros().get(z).getID());
				bw.newLine();
				
			}

			bw.write(pp.getRA());
			bw.newLine();
		}

		catch(IOException e){
			System.out.println("Erro: "+e.toString());
		}
	}

	//Metodo para ler o arquivo e adicionar os seus dados na lista de graduação
	//Recebe o arquivo, o a lista de pessoas na graduação e a lista de livros cadastrados
	public ArrayList<Graduacao> lerArquivo(File arquivop, ArrayList<Graduacao> P, ArrayList<Livro> L){

		try{
			//Cria o FileReader e o BufferedReader para a leitura do arquivo
			FileReader fr = new FileReader(arquivop);
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
				//Cria uma lista de livros reservados generica
				ArrayList<Livro> LA = new ArrayList<Livro>();
				//Cria uma pessoa na Graduação generica para receber os dados
				Graduacao pp = new Graduacao("",0,0,"",LA,"");
				
				//A pessoa vai receber os dados na sequencia que está escrito no arquivo
				//A sequencia é nome,idade,e-mail,quantidade de livros reservados, id dos livros reservados e por ultimo o RA
				pp.setNome(S.get(z));
				z++;

				int idd=Integer.parseInt(S.get(z));
				pp.setIdade(idd);
				z++;

				pp.setEmail(S.get(z));
				z++;

				idd=Integer.parseInt(S.get(z));
				pp.setQuant(idd);
				z++;

				pp.setLivros(LA);
				//O motivo de ter a quantidade de livros reservados é pra se saber quantas linhas serão dos IDs dos livros
				for(int y=0;y<pp.getQuant();y++){

					for(int h=0;h<L.size();h++){
						//O programa achando na Lista de Livros cadastrados o ID do livro escrito no arquivo...
						//O livro é adicionado na lista de livros reservados
						if(S.get(z).equals(L.get(h).getID())){

							LA.add(L.get(h));
							z++;
						}
					}

				}

				pp.setRA(S.get(z));
				//pp recebendo todas as informações é adicionado na lista de pessoas da Graduação
				P.add(pp);

			}
				//Depois de todas as pessoas terem sido colocadas na lista o FileReader e o BufferedReader são fechados
				br.close();
				fr.close();	
		}

		catch(IOException e){
			System.out.println("Erro: "+e.toString());
		}
		//Metodo retorna a lista de Pessoas da Graduação completa 
		return P;
	}

}
