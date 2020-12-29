package liteEngine.serialization;

import java.io.*;

/**
 * Utility class for deserialization
 * 
 * @author Nathan Wong
 */
public class Deserializer {

	/**
	 * Deserializes an object from a serialized file path
	 * 
	 * @param pathName path to the serialized file
	 * @return deserialized Object of type T
	 */
	public static <T extends Serializable> T DeserializeFromFile(String pathName) {
		try {
			if (!pathName.endsWith(".txt")) {
				throw new IllegalArgumentException(
						"~Deserializer.DeserializeFromFile: Invalid file type. A '.txt' extension must be provided.");
			}

			FileInputStream fileIn = new FileInputStream(pathName);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);

			@SuppressWarnings("unchecked")
			T obj = (T) objIn.readObject(); // This will throw an error if the cast type is different from the
											// deserialized object
			objIn.close();
			fileIn.close();

			return obj;
		} catch (IOException | ClassNotFoundException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}
}
