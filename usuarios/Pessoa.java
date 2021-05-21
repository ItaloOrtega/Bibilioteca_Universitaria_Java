//Pessoa pertence ao pacote usuarios
package usuarios;
//Importa todo o pacote biblioteca(Livro e Editora)
import biblioteca.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.File;
//importando as bibliotecas para poder utilizar ArrayList e Arquivo

//É uma classe abstrata por que ela não sera inicializada nenhum momento
public abstract class Pessoa{

	private String name,mail;
	private int age, quantity;
	private ArrayList<Livro> books = new ArrayList<Livro>();
	//Name = Nome da Pessoa, Mail = Endereço de E-Mail, Age = Idade da Pessoa, Quantity = Quantidade de Livros reservados pela Pessoa
	//Books = Lista de Livros que a Pessoa reservou

	public Pessoa(String nome,int idade,int quant,String email, ArrayList<Livro> livros){
	//Criando e colocando os valores na classe

		this.name=nome;
		this.age=idade;
		this.quantity=quant;
		this.mail=email;
		this.books=livros;
	}

	//Set do Nome da Pessoa
	public void setNome(String nome){

		this.name=nome;
	}

	//Set da Idade da Pessoa
	public void setIdade(int idade){

		this.age=idade;
	}

	//Set da Quantidade de Livros reservados pela Pessoa
	public void setQuant(int quant){

		this.quantity=quant;
	}

	//Set do endereço de E-Mail da Pessoa
	public void setEmail(String email){

		this.mail=email;
	}

	//Set da Lista de Livros que a Pessoa reservou
	public void setLivros(ArrayList<Livro> livros){

		this.books=livros;
	}

	//Get do Nome da pessoa
	public String getNome(){

		return this.name;
	}

	//Get da Idade da Pessoa
	public int getIdade(){

		return this.age;
	}

	//Get da Quantidade de Livros Reservados da Pessoa
	public int getQuant(){

		return this.quantity;
	}

	//Get do E-Mail da Pessoa
	public String getEmail(){

		return this.mail;
	}

	//Get da Lista de Livros Reservados da Pessoa
	public ArrayList<Livro> getLivros(){

		return this.books;
	}

}
