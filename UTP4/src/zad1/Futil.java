package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Futil {
	public static void processDir(String dirName, String resultFileName) {
		try {
			Path startingDir = Paths.get(dirName);
			Visitor visitor = new Visitor(Paths.get("./" + resultFileName));
			Files.walkFileTree(startingDir, visitor);
			visitor.cleanup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
