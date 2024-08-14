public class Pessoa {

    public Pessoa() {
    }

    public Pessoa(String nome, int idade, float peso) {
        super();
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }

    String nome;
    int idade;
    float peso;

    public String ToString() {
        return "Nome:" + nome + "Idade:" + idade + "peso" + peso;
    }

    public String pessoaToCsv() {
        return nome + ";" + idade + ";" + peso + ";";
    }
}
