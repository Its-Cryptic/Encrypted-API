package dev.cryptic.encryptedapi.util.model;

import com.mojang.math.Vector3f;
import net.minecraft.world.phys.Vec2;

import java.util.List;

public record Face(List<Vector3f> vertices, Vector3f normal, List<Vec2> uvs) {

}
