package com.raphfrk.bukkit.serverportcoreapi;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.bukkit.Location;
import org.bukkit.World;

@Entity
public class ServerPortLocation implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;

	public ServerPortLocation() {
	}

	public ServerPortLocation(String serverName, Location loc) {
		this(
				serverName, 
				(loc.getWorld()==null)?null:(loc.getWorld().getName()), 
				loc.getX(),
				loc.getY(),
				loc.getZ(),
				loc.getPitch(),
				loc.getYaw()
				);
	}

	public ServerPortLocation(ServerPortLocation loc) {
		this(loc.getServer(), loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
	}

	public ServerPortLocation(String server, String world, Double x, Double y, Double z, Float yaw, Float pitch) {
		this.server = server;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	@Id
	protected int id;
	
	protected String name;
	protected String server;
	protected String world;
	protected Double x;
	protected Double y;
	protected Double z;
	protected Float pitch;
	protected Float yaw;
	
	@Transient
	public String toString() {
		return "[" + id + ", " + name + ", " + server + ", " + world + ", " + x + ", " + y + ", " + z + ", " + pitch + ", " + yaw + "]";
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setWorld(String world) {
		this.world = world;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getWorld() {
		return world;
	}

	public String getServer() {
		return server;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getX() {
		return this.x;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getY() {
		return this.y;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	public Double getZ() {
		return this.z;
	}

	public void setYaw(Float yaw) {
		this.yaw = yaw;
	}

	public Float getYaw() {
		return this.yaw;
	}

	public void setPitch(Float pitch) {
		this.pitch = pitch;
	}

	public Float getPitch() {
		return this.pitch;
	}
	
	public Location getLocation(ServerPortCoreAPI serverPortCoreAPI) {

		String pluginServerName = serverPortCoreAPI.getLocalServerName();
		if(server != null && !server.equals(pluginServerName)) {
			return null;
		}
		World bukkitWorld = null;
		if(world != null) {
			bukkitWorld = serverPortCoreAPI.getLocalServer().getWorld(this.world);
			if(bukkitWorld == null) {
				return null;
			}
		}
		
		if(bukkitWorld == null) {
			bukkitWorld = serverPortCoreAPI.getLocalServer().getWorlds().get(0);
		}
		
		if(x==null || y==null || z==null) {
			Location spawnPosition = bukkitWorld.getSpawnLocation();
			spawnPosition.setX(spawnPosition.getX() + 0.5);
			spawnPosition.setZ(spawnPosition.getZ() + 0.5);
			return spawnPosition;
		} else if(pitch == null || yaw == null) {
			return new Location(bukkitWorld, x, y, z);
		} else {
			return new Location(bukkitWorld, x, y, z, yaw, pitch);
		}
	}

	public static ServerPortLocation fromLocation(String server, Location loc) {
		return new ServerPortLocation(server, loc);
	}

	public static ServerPortLocation fromLocation(ServerPortCoreAPI serverPortCoreAPI, Location loc) {
		
		String server = serverPortCoreAPI.getLocalServerName();
		return fromLocation(server, loc);
	}
	
	@Override
	public ServerPortLocation clone() {
		try {
			return (ServerPortLocation)super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
}