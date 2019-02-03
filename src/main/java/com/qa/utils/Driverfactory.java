package com.qa.utils;

public class Driverfactory
{
	private static String configPropertyFile;
	private static String gridPath;
	private static boolean isRemote;
	public static String getConfigPropertyFile()
	{
		return configPropertyFile;
	}
	public static void setConfigPropertyFile(String configPropertyFile)
	{
		Driverfactory.configPropertyFile = configPropertyFile;
	}
	public static String getGridPath()
	{
		return gridPath;
	}
	public static void setGridPath(String gridPath)
	{
		Driverfactory.gridPath = gridPath;
	}
	public static boolean isRemote()
	{
		return isRemote;
	}
	public static void setRemote(boolean isRemote)
	{
		Driverfactory.isRemote = isRemote;
	}
	
}
