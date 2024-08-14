import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class GravaArquivo {
    public void gravaArquivo(String conteudo) {
        File arFile = new File("exemplo_bala.csv");
        if (arFile.exists()) {
            try (Writer arquivo = new BufferedWriter(new FileWriter(arFile, true))) {
                arquivo.append(conteudo + "\n");
                arquivo.close();
                return;
            } catch (IOException e) {
                //e.printStackTrace();

            }
        }

        try (FileWriter writer = new FileWriter(arFile)) {
            writer.write("Nome;Idade;peso\n");
            writer.append(conteudo + "\n");
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
