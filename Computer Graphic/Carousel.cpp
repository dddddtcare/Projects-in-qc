#include <stdlib.h>
#include <stdio.h>
//#include <GLUT/glut.h>
#include <GL/glut.h>
#include <iostream>

#define ESC 27

using namespace std;

int spin = 0;
int speed = 1;
int lookx, looky, lookz, view = 0;
int star;

/*Lighting*/

void init(void){

	GLfloat light1_ambient[] = { 0.2, 0.2, 0.2, 1.0 };
	GLfloat light1_diffuse[] = { 1.0, 1.0, 1.0, 1.0 };
	GLfloat light1_specular[] = { 1.0, 1.0, 1.0, 1.0 };
	GLfloat light1_position[] = { -2, 2, 1, 1 };

	glClearColor(0.0, 0.0, 0.0, 0);
	glShadeModel(GL_SMOOTH);

	glLightfv(GL_LIGHT1, GL_AMBIENT, light1_ambient);
	glLightfv(GL_LIGHT1, GL_DIFFUSE, light1_diffuse);
	glLightfv(GL_LIGHT1, GL_SPECULAR, light1_specular);
	glLightfv(GL_LIGHT1, GL_POSITION, light1_position);
	glLightfv(GL_LIGHT1, GL_POSITION, light1_position);

	glEnable(GL_COLOR_MATERIAL);
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT1);

	glEnable(GL_DEPTH_TEST);
}


/*reshape*/

void reshape(int w, int h){
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, (GLfloat)w / (GLfloat)h, 0.5, 20);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

}

/*draw horses that rotatable */
void drawHorse(){
	glPushMatrix();
	GLUquadricObj*quadObj2;
	quadObj2 = gluNewQuadric();
	gluQuadricDrawStyle(quadObj2, GLU_FILL);

	glColor3f(1, 0.714, 0.75);
	//draw body
	glRotated(90, -0.8, -1, 0);
	glTranslated(0, 0, 0);
	gluCylinder(quadObj2, 0.06, 0.09, 0.25, 20, 6);
	glPopMatrix();
	//draw neck
	glPushMatrix();
	glRotated(90, -2.5, -1, 0);
	glTranslated(-0.1, 0, 0.2);
	gluCylinder(quadObj2, 0.01, 0.01, 0.2, 20, 6);
	glPopMatrix();
	//draw face
	glPushMatrix();
	glTranslated(-0.4, 0.3, 0);
	glRotated(270, 0, -1, 0);
	gluCylinder(quadObj2, 0.06, 0.09, 0.2, 20, 6);
	glPopMatrix();
	//draw legs
	glPushMatrix();
	glRotated(45, 1, 0, 1);
	gluCylinder(quadObj2, 0.01, 0.01, 0.3, 20, 6); //rear leg
	glPopMatrix();

	glPushMatrix();
	glTranslated(-0.2, 0.1, 0);
	glRotated(45, 1, -1, 1);
	gluCylinder(quadObj2, 0.01, 0.01, 0.45, 20, 6); //foreleg
	glPopMatrix();

}

/*draw the entity of Carousel*/

void drawConstructionOfCarousel(){
	
	GLUquadricObj*quadObj;
	quadObj = gluNewQuadric();
	gluQuadricDrawStyle(quadObj, GLU_FILL);
	glPushMatrix();

	/* draw base of Carousel */
	glColor3f(0.823, 0.4117, 0.117);
	glRotated(90, 1, 0, 0);
	glTranslated(0, 0, 1);
	gluCylinder(quadObj, 1.3, 1.3, 0.1, 20, 6);  //base

	glPushMatrix();
	glColor3f(0.823, 0.4117, 0.117);
	glTranslated(0, 0, 0);
	gluDisk(quadObj, 0, 1.3, 20, 6);            //top of the base
	glPopMatrix();

	glPushMatrix();
	glColor3f(0.823, 0.4117, 0.117);
	glTranslated(0, 0, 0.1);
	gluDisk(quadObj, 0, 1.3, 20, 6);             //bottom of the base
	glPopMatrix();

	/* draw roof of Carousel */
	glPushMatrix();
	glColor3f(1, 0.714, 0.75);
	glTranslated(0, 0, -1.5);
	gluCylinder(quadObj, 1, 1, 0.2, 20, 6);      //roof
	glPopMatrix();

	glPushMatrix();
	glColor3f(1, 0.714, 0.75);
	glTranslated(0, 0, -1.5);
	gluDisk(quadObj, 0, 1, 20, 6);               //top of roof
	glPopMatrix();

	glPushMatrix();
	glColor3f(1, 0.714, 0.75);
	glTranslated(0, 0, -1.3);
	gluDisk(quadObj, 0, 1, 20, 6);               //bottom of roof
	glPopMatrix();

	//draw central pillar

	glPushMatrix();
	glColor3f(1, 0.714, 0.75);
	glTranslated(0, 0, -1.5);

	glRotatef((GLfloat)spin, 0.0, 0.0, 1.0);  //rotate itself
	gluCylinder(quadObj, 0.1, 0.1, 1.6, 20, 6);
	glPopMatrix();
	/* draw the four rotatory handles */


	glPushMatrix();
	glColor3f(1, 0.714, 0.75);
	glRotatef((GLfloat)spin, 0.0, 0.0, 1.0);
	glTranslated(0.5, 0.5, -1.5);                   // handle 1
	gluCylinder(quadObj, 0.01, 0.01, 0.9, 20, 6);

	glTranslated(0.2, -0.1, 0.9);
	glRotated(-30, 1, 0, 0);
	drawHorse();
	glPopMatrix();

	glPushMatrix();
	glColor3f(1, 0.714, 0.75);
	glRotatef((GLfloat)spin, 0.0, 0.0, 1.0);
	glTranslated(0.5, -0.5, -1.5);
	gluCylinder(quadObj, 0.01, 0.01, 0.9, 20, 6);  //handle 2

	glTranslated(0.2, -0.1, 0.9);
	glRotated(-30, 1, 0, 0);
	drawHorse();
	glPopMatrix();

	glPushMatrix();
	glColor3f(1, 0.714, 0.75);
	glRotatef((GLfloat)spin, 0.0, 0.0, 1.0);
	glTranslated(-0.5, 0.5, -1.5);
	gluCylinder(quadObj, 0.01, 0.01, 0.9, 20, 6);  //handle 3

	glTranslated(0.2, -0.1, 0.9);
	glRotated(-30, 1, 0, 0);
	drawHorse();
	glPopMatrix();

	glPushMatrix();
	glColor3f(1, 0.714, 0.75);
	glRotatef((GLfloat)spin, 0.0, 0.0, 1.0);
	glTranslated(-0.5, -0.5, -1.5);
	gluCylinder(quadObj, 0.01, 0.01, 0.9, 20, 6);  //handle 4

	glTranslated(0.2, -0.1, 0.9);
	glRotated(-30, 1, 0, 0);
	drawHorse();
	glPopMatrix();

	glPopMatrix();       //pop from rotation
	glPopMatrix();         // pop from the second push
	glPopMatrix();        //pop from first push

}

/* draw decoration of roof*/

void drawTop(){
	
	glPushMatrix();
	GLUquadricObj*quadObj;
	quadObj = gluNewQuadric();
	gluQuadricDrawStyle(quadObj, GLU_FILL);

	glColor3f(1, 0.714, 0.75);
	glTranslated(0, 0.5, 0);
	glRotatef(90, -1.0, 0.0, 0.0);
	gluCylinder(quadObj, 0.8, 0.8, 0.2, 20, 6);
	glPopMatrix();

	glPushMatrix();
	glTranslated(0, 1.5, 0);
	glRotatef(270, -1.0, 0.0, 0.0);
	gluCylinder(quadObj, 0, 0.8, 1, 20, 6);
	glPopMatrix();

	glPushMatrix();
	glTranslated(0, 1.5, 0);
	glRotatef(270, -1.0, 0.0, 0.0);
	gluSphere(quadObj, 0.1, 20, 6);
	glPopMatrix();



	 



}
/* draw moon */
void drawMoon(){
	glPushMatrix();
	GLUquadricObj*quadObj;
	quadObj = gluNewQuadric();
	gluQuadricDrawStyle(quadObj, GLU_FILL);
	glColor3f(1, 1, 0);
	glTranslated(1, 1.5, -5);
	gluDisk(quadObj, 0, 0.3, 50, 10);

	glPopMatrix();
}

/*draw half flower circle decoration */
void drawHalfFlower(){
	glPushMatrix();
	glTranslated(0, 0.1, 0);
	glRotated(278, 0, 0, 1);
	for (int r = 0; r<180; r += 15){
		glPushMatrix();
		glColor3f(0.823, 0.4117, 0.117);
		glRotated(r, 0, 0, 0.5);
		glTranslated(0, 0.4, 1);
		glutSolidTorus(0.04, 0.04, 60, 20);
		glPopMatrix();
	}
	glPopMatrix();

}
/* draw all half flower circles decoration */
void drawAllHalfFlowers(){
	for (int r = 0; r<360; r += 60){
		glPushMatrix();

		glRotated(r, 0, 1, 0);
		glTranslated(0, 0, 0);
		drawHalfFlower();
		glPopMatrix();
	}

}


/* draw all big balls around carousel */
void drawAllBigBalls(){
	for (int r = 0; r<360; r += 30){

		if (r == 0 || r == 60 || r == 120 || r == 180 || r == 240 || r == 300 || r == 360){

		}
		else{
			glPushMatrix();
			glColor3f(0.823, 0.4117, 0.117);
			glRotated(r, 0, 1, 0);
			glTranslated(0, 0.1, 1.1);
			glutSolidSphere(0.2, 20, 12);

			glPopMatrix();
		}
	}

}
/* draw all pillars around carousel */
void drawAllPillars(){
	GLUquadricObj*quadObj1;
	quadObj1 = gluNewQuadric();
	gluQuadricDrawStyle(quadObj1, GLU_FILL);
	
	for (int r = 0; r<360; r += 60){

		if (r == 0 || r == 60 || r == 120 || r == 180 || r == 240 || r == 300 || r == 360){


			glPushMatrix();
			glColor3f(0.823, 0.4117, 0.117);
			glRotated(r, 0, 1, 0);
			glRotated(326, 0, 1, 0);
			glTranslated(1, -0.05, 0.5);
			glRotated(90, 1, 0, 0);
			gluCylinder(quadObj1, 0.05, 0.05, 1, 20, 6);

			glPopMatrix();

		}
		else{}
	}
	
	 
}


/* draw stars*/
void drawstar(){
	//star1
	glPushMatrix();
	glColor3f(1, 1, 0);
	glTranslated(1, 1, -5);
	glutSolidSphere(0.02, 20, 6);
	glPopMatrix();
	//star2
	glPushMatrix();
	glColor3f(1, 1, 0);
	glTranslated(-1, 1.5, -5);
	glutSolidSphere(0.02, 20, 6);
	glPopMatrix();
	//star3
	glPushMatrix();
	if (spin % 34 == 0 || spin % 69 == 0){
		glColor3f(1, 1, 0);
	}
	else{
		glColor3f(0, 0, 0.4);
	}
	glTranslated(1.5, 1.2, -5);
	glutSolidSphere(0.02, 20, 6);
	glPopMatrix();
	//star 4
	glPushMatrix();
	if (spin % 34 == 0 || spin % 69 == 0){
		glColor3f(1, 1, 0);
	}
	else{
		glColor3f(0, 0, 0.4);
	}
	glTranslated(0.1, 1.7, -5);
	glutSolidSphere(0.02, 20, 6);
	glPopMatrix();
	//star 5
	glPushMatrix();
	if (spin % 34 == 0 || spin % 69 == 0){
		glColor3f(1, 1, 0);
	}
	else{
		glColor3f(0, 0, 0.4);
	}
	glTranslated(-1.4, 1, -5);
	glutSolidSphere(0.02, 20, 6);
	glPopMatrix();
}

/* draw night sky*/

void drawNightSky(){
	glPushMatrix();

	glColor3f(0, 0, 1);
	glTranslated(0, 0, -11);

	glBegin(GL_QUADS);
	glVertex3f(-100, 0, 0.0);
	glVertex3f(-100, 100.0, 0.0);
	glVertex3f(100, 100, 0.0);
	glVertex3f(100, 0, 0.0);
	glEnd();

	glPopMatrix();
}

//*  draw night grassland
void drawNightGrassland(){
	glPushMatrix();
	glColor3f(0, 1, 0);

	glTranslated(0, 0, -11);

	glBegin(GL_QUADS);
	glVertex3f(-100, -100, 0.0);
	glVertex3f(-100, 0, 0.0);
	glVertex3f(100, 0, 0.0);
	glVertex3f(100, -100, 0.0);
	glEnd();

	glPopMatrix();

}

void renderScene(void){

	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glLoadIdentity();
	drawNightSky();
	drawNightGrassland();
	drawstar();
	drawMoon();

	gluLookAt(lookx, looky, lookz + 5, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

	drawConstructionOfCarousel();
	drawTop();
	drawAllHalfFlowers();
	drawAllBigBalls();
	drawAllPillars();

	glutSwapBuffers();
}

void idle(void){


	if (spin >= 360) spin = 0;
	else
		spin = (spin + speed) % 360;


	glutPostRedisplay();

}

void keyboard(unsigned char key, int x, int y){

	if (key == ESC || key == 'q' || key == 'Q')
		exit(0);
	if (key == 'A' || key == 'a'&& speed<9)
	{
		speed++;
		cout << " Speed now is " << speed << endl;
		glutPostRedisplay();

	}
	if ((key == 'd' || key == 'D') && speed > 1)
	{
		speed--;
		cout << " Speed now is " << speed << endl;
		glutPostRedisplay();
	}

	if (key == 'Z'&&lookz>-3){
		lookz--;
		glutPostRedisplay();

	}
	if (key == 'z'&& lookz<3){
		lookz++;
		glutPostRedisplay();

	}

	if (key == 'c' || key == 'C'){


		if (key == 'Z'&&lookz>-3){
			lookz--;
			glutPostRedisplay();

		}
		if (key == 'z'&& lookz<6){
			lookz++;
			glutPostRedisplay();

		}

		switch (view){

		case 0:lookx = 0; looky = 4; lookz = 0;   //from up
			break;
		case 1:lookx = 0; looky = 0; lookz = -3;   //close shot
			break;
		case 2:lookx = 3; looky = 0; lookz = 0;   //side
			break;
		case 3:lookx = 0; looky = 0; lookz = 0;
			break;
		}

		view = (view + 1) % 4;
		glutPostRedisplay();

	}
}

int main(int argc, char** argv){
	cout << "************Welcome***********************\n";
	cout << "ZOOM IN:            PRESS 'Z' \n";
	cout << "ZOOM OUT:           PRESS 'z' \n";
	cout << "CHANGE VIEW POINT:  PRESS 'c'or'C' \n";
	cout << "TO INCREASE SPEED:  PRESS 'a'or'A' \n";
	cout << "TO DECREASE SPEED:  PRESS 'd'or'D' \n";
	cout << "TO QUIT:            PRESS 'ESC'or'q'or'Q'\n";
	cout << "ENJOY THE CAROUSEL NIGHT!\n";
	cout << "******************************************\n";
	//ini
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(600, 600);
	glutInitWindowPosition(200, 100);
	glutCreateWindow("Carousel_RuiXu");
	init();

	//callbacks
	glutDisplayFunc(renderScene);
	glutIdleFunc(idle);
	glutKeyboardFunc(keyboard);
	glutReshapeFunc(reshape);

	glutMainLoop();

	return 0;
}