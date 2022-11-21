/*
========================================
| Nathan A Lalli                       |
| Wednesday, April 1st                 |
| Programming Assignment #6            |
| Desccription: Drawing                |
========================================
*/
#include <iostream>
#include <fstream>
#include <SFML\Graphics.hpp>
#include "SettingsMgr.h"
#include "ShapeMgr.h"
#include "SettingsUI.h"
#include "DrawingUI.h"

using namespace std;
using namespace sf;

// Finish this code. Other than where it has comments telling you to 
// add code, you shouldn't need to add any logic to main to satisfy
// the requirements of this programming assignment

int main()
{
	const int WINDOW_WIDTH = 800;
	const int WINDOW_HEIGHT = 600;

	RenderWindow window(VideoMode(WINDOW_WIDTH, WINDOW_HEIGHT), "Drawing");
	window.setFramerateLimit(120);

	SettingsMgr settingsMgr(Color::Magenta, SettingsMgr::ShapeEnum::CIRCLE);
	SettingsUI  settingsUI(&settingsMgr);
	ShapeMgr    shapeMgr;
	DrawingUI   drawingUI(Vector2f(250, 10));

	ifstream input;
	input.open("shapes.bin", ios::in || ios::binary);
	if (input.fail())
	{
		cout << "There was an error trying to open the input file.";
		return 1;
	}
	else
	{
		shapeMgr.loadin(input);
		input.close();
	}

	while (window.isOpen())
	{
		Event event;

		while (window.pollEvent(event))
		{
			if (event.type == Event::Closed)
			{
				ofstream vectorfile;

				vectorfile.open("shapes.bin", ios::out || ios::binary);

				if (vectorfile.fail())
				{
					cout << "There was an error opening the vector file.";
					return 2;
				}
				else
				{
					shapeMgr.sendout(vectorfile);
					vectorfile.close();
				}

				window.close();

			}
			else if (event.type == Event::MouseButtonReleased)
			{
				// The following gets the x,y position of the mouse and passes it to
				// the SettingsUI handleMouseUp function. This function should 
				// check to see if the mouse is over one of the "settings" buttons and
				// do the thing associated with that button.

				Vector2f mousePos = window.mapPixelToCoords(Mouse::getPosition(window));
				settingsUI.handleMouseUp(mousePos);
			}
			else if (event.type == Event::MouseMoved && Mouse::isButtonPressed(Mouse::Button::Left))
			{
				
				Vector2f mousePos = window.mapPixelToCoords(Mouse::getPosition(window));
				// check to see if mouse is in the drawing area
				if (drawingUI.isMouseInCanvas(mousePos))
				{
					// add a shape to the list based on current settings
					shapeMgr.addShape(mousePos, settingsMgr.getCurShape(), settingsMgr.getCurColor());
				}
			}
		}

		// The remainder of the body of the loop draws one frame of the animation
		window.clear();

		// this should draw the left hand side of the window (all of the settings info)
		settingsUI.draw(window);

		// this should draw the rectangle that encloses the drawing area, then draw the
		// shapes. This is passed the shapeMgr so that the drawingUI can get the shapes
		// in order to draw them. This redraws *all* of the shapes every frame.
		drawingUI.draw(window, &shapeMgr);

		window.display();
	} // end body of animation loop

	return 0;
}