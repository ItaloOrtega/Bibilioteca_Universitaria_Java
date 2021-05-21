//Importa todo o pacote biblioteca(Livro e Editora)
import biblioteca.*;
//Importa todo o pacote usuarios(Pessoa,Graduacao e Pos_Graduacao)
import usuarios.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.io.File;
//importando as bibliotecas para poder utilizar ArrayList e Arquivo

public class Menu{

	public static void main(String []args){
		//Nome = Noma da Pessoa/Editora/Autor - titulo do Livro/ ; ID = Codigo de cadastro das Pessoas/Editoras/Livros
		//End = Endereço da editora; E-mail = e-mail das pessoas
		//Idade = Idade das pessoas; OP = Operação do menu.
		String nome,id,end,email;
		int idade,op;

		//Criação das Listas de Gradução,Pós-Graduação, Editoras e de Livros.
		ArrayList<Graduacao> G = new ArrayList<Graduacao>();
		ArrayList<Pos_Graduacao> PG = new ArrayList<Pos_Graduacao>();
		ArrayList<Editora> E = new ArrayList<Editora>();
		ArrayList<Livro> L = new ArrayList<Livro>();

		try{
			//Criação dos arquivos de Graduação,Pós-Graduação, Editoras e de Livros.
			File arquivog = new File("Graduacao.txt");
			File arquivopg = new File("Pos_Graduacao.txt");
			File arquivoe = new File("Editoras.txt");
			File arquivol = new File("Livros.txt");

			//Esses Ifs servem para verificar se os arquivos já existem ou não, se eles já foram criados é chamado os métodos lerArquivo de cada classe, para que cada lista receba os dados que ja foram colocados anteriormente.
			//O metodo lerArquivo sempre vai receber o arquivo respectivo daquela lista e a propria lista
			//E são criadas variaveis genericas para que o programa funcione
			if(arquivoe.createNewFile()==false){
				Editora ee = new Editora("","","");
				E=ee.lerArquivo(arquivoe,E);
			}

			if(arquivol.createNewFile()==false){
				Editora ee = new Editora("","","");
				Livro ll = new Livro("","","",ee);
				L=ll.lerArquivo(arquivol,L,E);
			}

			if(arquivopg.createNewFile()==false){
				ArrayList<Livro> LA = new ArrayList<Livro>();
				Pos_Graduacao pp = new Pos_Graduacao("",0,0,"",LA,"");
				PG=pp.lerArquivo(arquivopg,PG,L);
			}

			if(arquivog.createNewFile()==false){
				ArrayList<Livro> LA = new ArrayList<Livro>();
				Graduacao gg = new Graduacao("",0,0,"",LA,"");
				G=gg.lerArquivo(arquivog,G,L);
			}
			//Menu do programa, onde o usuário escolhe oq deseja fazer
			do{
				System.out.println("\n----MENU----\n1 - Cadastrar Pessoas\n2 - Cadastrar Editora\n3 - Cadastrar Livro\n4 - Reservar Livro\n5 - Devolver Livro\n6 - Ver Pessoas\n7 - Ver Editoras\n8 - Ver Livros\n9 - Sair\n");
				Scanner ler = new Scanner(System.in);
				op=ler.nextInt();
				switch(op){
					//Cadastrar uma pessoa
					case 1:

					//Lista generica de livros, que é utilizada para guardar as futuras reservas 
					ArrayList<Livro> LA = new ArrayList<Livro>();

					System.out.println("\nDigite seu nome:");
					Scanner lern = new Scanner(System.in);
					nome=lern.nextLine();

					System.out.println("\nDigite sua idade:");
					idade=ler.nextInt();

					System.out.println("\nDigite seu e-mail:");
					email=ler.next();

					int resp=0;
					//O usuário responde se ele é aluno da graduação ou da pós
					//E dependendo da resposta ele vai para uma lista diferente
					//Após ele responder, o ID é criado automaticamente para que não tenha ID's duplicados
					//E o cadastro é adicionado na lista respectiva
					do{

						System.out.println("\nVocê é aluno da Graduação ou da Pós-Graduação?\n\n1 - Graduação ou 2 - Pós-Graduação\n");
						resp=ler.nextInt();

						if((resp!=1)&&(resp!=2)){
							System.out.println("Digite uma opção valida!\n");
						}

					}while((resp!=1)&&(resp!=2));

					//Ele sendo da Graduação o ID dele começa com a letra "G"
					if(resp==1){
						id=("G"+(G.size()+1));
						System.out.println("\nO ID é "+id+".");
						Graduacao gg = new Graduacao(nome,idade,0,email,LA,id);
						G.add(gg);
					}

					//Ele sendo da Pós-Graduação o ID dele começa com a letra "P"
					if(resp==2){

						id=("P"+(PG.size()+1));
						System.out.println("\nO ID é "+id+".");
						Pos_Graduacao pg = new Pos_Graduacao(nome,idade,0,email,LA,id);
						PG.add(pg);
					}
					break;
					//Cadastrar uma nova Editora
					case 2:
						Editora ee = new Editora("","","");
						System.out.println("\nDigite o nome da editora:");
						Scanner lernn = new Scanner(System.in);
						nome=lernn.nextLine();
						ee.setNome(nome);

						System.out.println("\nDigite o endereço:");
						Scanner lere = new Scanner(System.in);

						end=lere.nextLine();
						ee.setEnd(end);
						//Os ID's das editoras começam com a letra "E"
						id=("E"+(E.size()+1));

						System.out.println("\nO ID é "+id+".");

						ee.setID(id);

						E.add(ee);

						System.out.println("\nEditora Cadastrada!");

						//Após cadastrar uma editora ela ja é colocado no arquivo "Editoras.txt"
						E.get(E.size()-1).adicionarArquivo(arquivoe,ee);
					break;

					//Cadastrar Livro
					case 3:
						//Se o não tiver nenhuma editora cadastrada
						//Não vai dar pra cadastrar nenhum livro e aparecerá uma mensagem de erro
						if(E.size()==0){
							System.out.println("\nNão tem nenhuma editora cadastrada!\n");
						}
						//Tendo pelo menos uma editora cadastrada o cadastro é iniciado 
						else{
							Editora eee = new Editora("","","");
							Livro ll = new Livro("","","",eee);

							System.out.println("\nDigite o Título:");
							Scanner lernnn = new Scanner(System.in);
							nome=lernnn.nextLine();

							ll.setTitulo(nome);

							System.out.println("\nDigite o nome do Autor:");
							Scanner lera = new Scanner(System.in);
							nome=lera.nextLine();

							ll.setAutor(nome);
					
							System.out.println("\nDigite o ID da Editora:");
							id=ler.next();
							//Váriavel criada só para mostrar ou não uma mensagem de erro
							int resu=0;
							//Após digitar o ID da editora que o livro pertence, a lista de Editoras é percorrida
							//Pois é necessario ligar o livro com a Editora 
							for(int y=0;y<E.size();y++){
								//Se o ID digitado for igual a um ID da lista de Editoras
								//A editora dessa posição da lista é colocada na variavel Editora de Livro
								if(id.equals(E.get(y).getID())){
									resu=1;
									ll.setEditora(E.get(y));

									//Os ID's de Livros começam com L
									id=("L"+(L.size()+1));

									System.out.println("\nO ID é "+id+".");

									ll.setID(id);

									System.out.println("\nLivro Cadastrado!");

									L.add(ll);
									//Após cadastrrar um Livro ele é colocado em "Livros.txt"
									L.get(L.size()-1).adicionarArquivo(arquivol,ll);
								}
							}
							//Mensagem de Erro se não acha uma editora
							if(resu==0){
								System.out.println("\nEditora não encontrada!\n");
							}
						}
					break;

					//Reservar um livro
					case 4:
					//Se não tem nenhum livro cadastrado e nenhuma pessoa do grupo de Graduação ou da Pós...
					//essa opção não inicia
						if((L.size()==0)||((PG.size()==0)&&(G.size()==0))||((L.size()==0)&&(PG.size()==0)&&(G.size()==0))){
							System.out.println("\nNão é possível!\n");
						}
						//Tendo pelo menos um livro e uma pessoa cadastrada...
						//Essa opção inicia.
						else{
							System.out.println("\nDigite o ID da Pessoa:");
							id=ler.next();
							//resup = se o id digitado foi encontrado em alguma das listas de alunos
							int resup=0;
							if(id.charAt(0) == 'P'){
								for(int y=0;y<PG.size();y++){
								//O id foi encontrado na lista de pessoas da pós..
								//mas ele não pode reservar mais nenhum livro pois chegou no limite de 10 livros
									if((id.equals(PG.get(y).getCCO()))&&PG.get(y).getQuant()==10){
										resup=1;
										System.out.println("\nVocê não pode reservar mais livro!\n");
									}
									//O id sendo encontrado na lista da pós, e seu numero de reservas sendo menor que 10...
									//É iniciado o metodo reservar
									if((id.equals(PG.get(y).getCCO()))&&PG.get(y).getQuant()<10){
										resup=1;
										//aux = Recebe o valor do tamanho da lista de livros reservados
										int aux=0;
										aux=PG.get(y).getLivros().size();
										//Esse metodo é explicado dentro da classe Pos_Graduacao
										PG.get(y).setLivros(PG.get(y).Reservar(PG.get(y),L));
										//Se o tamanho da lista de livros reservados mudou...
										//ou seja, diferente de aux...
										//Significa que a reserva foi um sucesso...
										//e que pode aumentar a quantidade de livros reservados da pessoa.
										if(PG.get(y).getLivros().size()>aux){
											PG.get(y).setQuant((PG.get(y).getQuant())+1);
										}
										break;
									}
								}
							}
							//A mesma coisa que a parte comentada acima, mas só que agora na lista de Graduacao
							if(id.charAt(0) == 'G'){
								for(int y=0;y<G.size();y++){
									if((id.equals(G.get(y).getRA()))&&G.get(y).getQuant()==5){
										resup=1;
										System.out.println("\nVocê não pode reservar mais livro!\n");
									}
									if((id.equals(G.get(y).getRA()))&&G.get(y).getQuant()<5){
										resup=1;
										int aux=0;
										aux=G.get(y).getLivros().size();

										G.get(y).setLivros(G.get(y).Reservar(G.get(y),L));

										if(G.get(y).getLivros().size()>aux){
											G.get(y).setQuant((G.get(y).getQuant())+1);
										}
										break;
									}
								}
							}
							//Se não foi encontrado o ID digitado em nenhuma luz, aparece essa mensagem de erro
							if(resup==0){
								System.out.println("\nNenhuma pessoa foi encontrada!\n");
							}
						}
					break;
					//Devolução de livros
					case 5:
						//Não tendo livros e/ou alunos cadastrados não é possivel devolver nada
						if((L.size()==0)||((PG.size()==0)&&(G.size()==0))||((L.size()==0)&&(PG.size()==0)&&(G.size()==0))){
							System.out.println("\nNão é possível!\n");
						}
						//Existindo no minimo um aluno e um livro cadastrado
						else{
							System.out.println("\nDigite o ID da Pessoa:");//ID para encontrar o cadastro do aluno
							id=ler.next();
							int resup=0;
							if(id.charAt(0) == 'G'){
								for(int y=0;y<G.size();y++){//For para encontrar o aluno dentro da lista de alunos de graduação
									if((id.equals(G.get(y).getRA()))&&(G.get(y).getQuant()!=0)){//Encontrando o ID e o aluno possuindo ao minimo 1 livro reservado
										resup=1;
										int aux=0;
										aux=G.get(y).getLivros().size();//Recebe a quantidade de livros reservados do aluno					
										G.get(y).setLivros(G.get(y).Devolver(G.get(y)));//Livro é retornado
										if(G.get(y).getLivros().size()<aux){//Quantidade de livros reservados do aluno atualizada 
											G.get(y).setQuant((G.get(y).getQuant()-1));
										}
										break;	
									}
								}
							}
							if(id.charAt(0) == 'P'){//Ocorre a mesma coisa no caso anterior, so que é na lista de alunos de Pós-Graduação
								for(int y=0;y<PG.size();y++){
									if((id.equals(PG.get(y).getCCO()))&&(PG.get(y).getQuant()!=0)){
										resup=1;
										int aux=0;
										aux=PG.get(y).getLivros().size();					

										PG.get(y).setLivros(PG.get(y).Devolver(PG.get(y)));

										if(PG.get(y).getLivros().size()<aux){
											PG.get(y).setQuant((PG.get(y).getQuant()-1));
										}
										break;	
									}
								}
							}
							if(resup==0){//Mensagem de erro se a pessoa não foi encontrada
								System.out.println("\nNenhuma pessoa foi encontrada!\n");
							}
						}
					break;
					
					//Printa as pessoas de ambas as listas de alunos
					case 6:
						//Se nenhuma pessoa foi cadastrada aparece essa mensagem
						if((G.size()==0)&&(PG.size()==0)){
							System.out.println("Nenhuma pessoa cadastrada!");
						}
						//Se tiver alguem cadastrado na lista de Graduação...
						//todos nessa lista serão printados
						if(G.size()!=0){
							System.out.println("\n---Graduação---");
							G.get(0).Printar(G);
						}
						//Se tiver alguem cadastrado na lista de Pós-Graduação...
						//todos nessa lista serão printados
						if(PG.size()!=0){
							System.out.println("\n---Pós-Graduação---");
							PG.get(0).Printar(PG);
						}
					break;

					//Printar as Editoras
					case 7:
					//Se nenhuma editora foi cadastrada essa mensagem aparece
						if(E.size()==0){
							System.out.println("Nenhuma editora cadastrada!");
						}
						//Tendo alguma editora cadastrada, ela é mostrada
						else{
							for(int z=0;z<E.size();z++){
								System.out.println("\nEditora: "+E.get(z).getNome()+"\nEndereço: "+E.get(z).getEnd()+"\nID: "+E.get(z).getID());
							}
						}	
					break;

					//Printar os livros
					case 8:
						//Se nenhum livro foi cadastrado essa mensagem aparece
						if(L.size()==0){
							System.out.println("Nenhum livro cadastrado!");
						}
						//Tendo algum livro cadastrado, ele é mostrado
						else{
							for(int z=0;z<L.size();z++){
								System.out.println("\nTítulo: "+L.get(z).getTitulo()+"\nAutor: "+L.get(z).getAutor()+"\nEditora: "+L.get(z).getEditora().getNome()+"\nID: "+L.get(z).getID());
							}
						}
					break;
				}
			//op sendo igual a 9 o programa sera fechado
			}while(op!=9);
			//As pessoas das listas de Graduação e Pós são colocadas em seus respectivos arquivos somente quando...
			//o usuário sai do loop, pois ai as pessoas cadastradas não seram modificadas...
			//Pois diferente de Livros e Editoras, as pessoas são modificadas sempre...
			//Mudando a quantidade de livros reservados e os proprios livros.

			//É criado os bufferedwriter e filewritter para cada lista
			FileWriter fw = new FileWriter(arquivopg,false);
			BufferedWriter bw = new BufferedWriter(fw);

			//Percorre a lista e adiciona a pessoa dessa posição no arquivo
			for(int y=0;y<PG.size();y++){
				//O metodo adicionarArquivo está comentado em Pos_Graduacao
				PG.get(y).adicionarArquivo(arquivopg,PG.get(y),fw,bw);
			}
			//O filewritter e bufferedwriter são fechados
			bw.close();
			fw.close();

			//A mesma parte acima acontece novamente, mas só que agora com a lista de Graduacao
			FileWriter fwg = new FileWriter(arquivog,false);
			BufferedWriter bwg = new BufferedWriter(fwg);

			for(int y=0;y<G.size();y++){
				G.get(y).adicionarArquivo(arquivog,G.get(y),fwg,bwg);
			}
			bwg.close();
			fwg.close();
		}

		catch(IOException e){
			System.out.println("Erro: "+e.toString());
		}
	}
}
