package liteEngine.serialization;

import java.io.*;

/**
 * Utility class for serialization
 * 
 * @author Nathan Wong
 */
public final class Serializer {

	/**
	 * Serializes an object to a file
	 * 
	 * @param obj      object to serialize - must implement java.io.Serializable
	 * @param pathName path to write the file to
	 */
	public static <T extends Serializable> void SerializeToFile(T obj, String pathName) {
		try {
			//TODO Technically will still work with other extensions (although it will always write bytes down)
			if (!pathName.endsWith(".txt")) {
				throw new IllegalArgumentException(
						"~Serializer.SerializeToFile: Invalid file type. A '.txt' extension must be provided.");
			}

			FileOutputStream fileOut = new FileOutputStream(pathName);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

			objOut.writeObject(obj);
			objOut.close();
			fileOut.close();

			System.out.println("~Serializer: Serialized " + obj.getClass().getName() + " to " + pathName);
		} catch (IOException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
