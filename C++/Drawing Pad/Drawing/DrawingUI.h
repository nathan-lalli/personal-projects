#pragma once
#include <SFML/Graphics.hpp>
#include <iostream>
#include "ShapeMgr.h"

using namespace std;
using namespace sf;

class DrawingUI
{
private:
	ShapeMgr* ptr;
	RectangleShape canvas;

public:
	// we are passed the upper left point in the window where the
	// drawing canvas should be located
	DrawingUI(Vector2f upperLeft)
	{
		canvas.setPosition(upperLeft.x, upperLeft.y);
	}

	// draws the box around the canvas area, then uses ShapeMgr to 
	// get the shapes and draw them
	void draw(RenderWindow& win, ShapeMgr *mgr)
	{
		ptr = mgr;

		canvas.setSize(Vector2f(540, 580));
		canvas.setFillColor(Color::White);
		win.draw(canvas);

		ptr->getVector();

		for (int i = 0; i < ptr->getVector().size(); i++)
		{
			ptr->getVector()[i]->draw(win);
		}
	}
	
	// returns true if the mouse is somewhere within the canvas
	bool isMouseInCanvas(Vector2f mousePos)
	{
		bool flag = false;

		if (mousePos.x > 250 && mousePos.x < 790 && mousePos.y > 10 && mousePos.y < 590)
		{
			flag = true;
		}

		return flag;
	}
};

