package com.raphfrk.bukkit.serverportcoreapi;

import org.bukkit.Server;

public interface ServerPortCoreAPI {
	
	/**
	 * Returns the name of the service provider
	 *
	 * @return the name of the provider
	 */

	public String getProviderName();
	
	String getLocalServerName();
	
	Server getLocalServer();
	
}
