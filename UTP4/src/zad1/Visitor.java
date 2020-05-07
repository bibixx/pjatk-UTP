package zad1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Visitor implements FileVisitor<Path> {
	private BufferedWriter writer;

	public Visitor(Path outPath) throws IOException {
		System.out.println(outPath);
		writer = new BufferedWriter(
			new OutputStreamWriter(
				new FileOutputStream(
					outPath.toFile()
				),
				StandardCharsets.UTF_8
			)
		);
	}
	
	public void cleanup() throws IOException {
		writer.close();
	}

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }
 
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        BufferedReader in = new BufferedReader(
    		new InputStreamReader(
				new FileInputStream(
					file.toString()),
					"cp1250"
				)
    		);

        String line;

        while ((line = in.readLine()) != null) {
        	writer.write(line);
        	writer.newLine();
        }
        
        in.close();

        return FileVisitResult.CONTINUE;
    }
 
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {       
        return FileVisitResult.CONTINUE;
    }
 
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {    
        return FileVisitResult.CONTINUE;
    }
}