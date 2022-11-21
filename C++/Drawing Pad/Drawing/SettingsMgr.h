#pragma once
#include <SFML/Graphics.hpp>
#include <iostream>

using namespace std;
using namespace sf;


// finish this code; add functions, data as needed

class SettingsMgr
{
private:
	Color currentcolor;

public:
	enum ShapeEnum { CIRCLE, SQUARE }; // which shape

	ShapeEnum currentshape;

	// Initialize the SettingsMgr with the desired default values
	// for drawing color and which shape to use.
	SettingsMgr(Color startingColor, ShapeEnum startingShape )
	{
		currentcolor = startingColor;
		currentshape = startingShape;
	}

	// return the current drawing color
	Color getCurColor()
	{
		return currentcolor; // just to make it compile 
	}

	// return the current drawing shape
	ShapeEnum getCurShape()
	{
		return currentshape;
	}

	//Sets the current color that is being used
	void setCurColor(Color color)
	{
		currentcolor = color;
	}

	//Sets the current shape that is being used
	void setCurShape(ShapeEnum curshape)
	{
		currentshape = curshape;
	}

	//records the color that is being used at the time and stores it as an integer
	unsigned colorrecord()
	{
		unsigned recordcolor = getCurColor().toInteger();
		return recordcolor;
	}
};
