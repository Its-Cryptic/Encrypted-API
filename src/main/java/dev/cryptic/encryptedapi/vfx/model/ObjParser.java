package dev.cryptic.encryptedapi.vfx.model;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import org.joml.Vector3f;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjParser {

    public List<Vector3f> vertices = new ArrayList<>();
    public List<Vertex> vertexList = new ArrayList<>();
    public List<Vector3f> normals = new ArrayList<>();
    public List<Vec2> uvs = new ArrayList<>();
    public List<Face> faces = new ArrayList<>();

    public void parseObjFile(ResourceLocation modelLocation) throws IOException {
        InputStream inputStream = Minecraft.getInstance().getResourceManager().getResource(modelLocation).get().open();
        if (inputStream == null) {
            throw new IOException("Resource not found: " + modelLocation);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("v ")) {
                String[] tokens = line.split(" ");
                float x = Float.parseFloat(tokens[1]);
                float y = Float.parseFloat(tokens[2]);
                float z = Float.parseFloat(tokens[3]);
                vertices.add(new Vector3f(x, y, z));
                vertexList.add(new Vertex(new Vector3f(x, y, z)));
            } else if (line.startsWith("vn ")) {
                String[] tokens = line.split(" ");
                float x = Float.parseFloat(tokens[1]);
                float y = Float.parseFloat(tokens[2]);
                float z = Float.parseFloat(tokens[3]);
                normals.add(new Vector3f(x, y, z));
            } else if (line.startsWith("vt ")) {
                String[] tokens = line.split(" ");
                float u = Float.parseFloat(tokens[1]);
                float v = Float.parseFloat(tokens[2]);
                uvs.add(new Vec2(u, v));
            } else if (line.startsWith("f ")) {
                String[] tokens = line.split(" ");
                List<Vector3f> faceVertices = new ArrayList<>();
                Vector3f faceNormal = null; // Assuming a single normal for simplicity; adjust if needed
                List<Vec2> faceUVs = new ArrayList<>();

                for (int i = 1; i < tokens.length; i++) {
                    String[] parts = tokens[i].split("/");
                    int vertexIndex = Integer.parseInt(parts[0]) - 1;
                    int textureIndex = Integer.parseInt(parts[1]) - 1;
                    faceVertices.add(vertices.get(vertexIndex));
                    faceUVs.add(uvs.get(textureIndex));
                    if (i == 1) { // Adjust based on how you decide to handle normals
                        int normalIndex = Integer.parseInt(parts[2]) - 1;
                        faceNormal = normals.get(normalIndex);
                    }
                }

                faces.add(new Face(faceVertices, faceNormal, faceUVs));
            }
        }
        reader.close();
    }

    public ArrayList<Face> getFaces() {
        return (ArrayList<Face>) faces;
    }
}