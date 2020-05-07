package zad2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Futil {
	public static void processDir(String dirName, String resultFileName) {
		// TODO Auto-generated method stub
		Predicate<Path> isFile = Files::isRegularFile;
		try {
			Stream<String> stream = Files.walk(Paths.get(dirName)).filter(isFile).flatMap(p -> {
				try {
					return Files.lines(p, Charset.forName("cp1250"));
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}).filter(l -> l != null);

			Files.write(Paths.get("./" + resultFileName), (Iterable<String>) stream::iterator, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
