package servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author wanghc
 */
public class WebappClassLoader extends ClassLoader {

    private final String classes;

    private final String lib;

    public WebappClassLoader(String basePath) {
        this.classes = basePath + "/WEB-INF/classes/";
        this.lib = basePath + "/WEB-INF/lib/";
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = null;

        Path path = Path.of(classes + name.replace('.', '/') + ".class");
        if (Files.exists(path)) {
            try {
                bytes = Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            bytes = findClassFromLib(name);
        }
        if (bytes == null) {
            throw new ClassNotFoundException();
        }

        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] findClassFromLib(String name) {
        File[] libFiles = new File(lib).listFiles();
        if (libFiles == null || libFiles.length <= 0) {
            return null;
        }
        for (File file : libFiles) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                byte[] bytes = new byte[0];
                try {
                    bytes = findClassFromJar(new JarFile(file), name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bytes != null) {
                    return bytes;
                }
            }
        }

        return null;
    }

    private byte[] findClassFromJar(JarFile jar, String name) throws IOException {
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String entryName = jarEntry.getName();
            if (entryName.endsWith(".class")) {
                String className = entryName.replace(".class", "").replace("/", ".");
                if (!className.equals(name)) {
                    continue;
                }
                InputStream inputStream = jar.getInputStream(jarEntry);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesNumRead;
                while ((bytesNumRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesNumRead);
                }
                byte[] bytes = outputStream.toByteArray();
                inputStream.close();
                return bytes;
            }
        }

        return null;
    }

}
