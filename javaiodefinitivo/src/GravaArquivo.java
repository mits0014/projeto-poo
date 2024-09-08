import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class GravaArquivo {
    public void gravaLinhas(String[] linhas) {
        File arFile = new File("exemplo_bala.csv");
        if (arFile.exists()) {
            try{
                Writer arquivo = new BufferedWriter(new FileWriter(arFile, true));
                for (String string : linhas) {
                    arquivo.append(string +";");
                }
                arquivo.append("\n");
                arquivo.close();
                return;
            }catch (IOException e) {
            e.printStackTrace();    
        }
        System.out.println("arquivo não encontrado, por favor defina as colunas");
        }
    }
    public void  gravaColuna(String[] colunas){
        File arFile = new File("exemplo_bala.csv");
        if (arFile.exists()) {
            System.out.println("arquivo já existente, por favor apague o arquivo e reinicie o sistema");
            return;
        }
        try (FileWriter writer = new FileWriter(arFile)) {
            for (String string : colunas) {
                writer.write(string + ";");
            }
            writer.append("\n");
            writer.close();
            System.out.println("Colunas definidas com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
