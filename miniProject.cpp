#include <iostream>
#include <cmath>

using namespace std;

void solveLinearEquationInTwoVariables()
{
    double a, b, c, d, e, f;
    double x, y;

    cout << "Enter the coefficients and constants for the equations ax + by = c and dx + ey = f:" << endl;
    cout << "Enter a: ";
    cin >> a;
    cout << "Enter b: ";
    cin >> b;
    cout << "Enter c: ";
    cin >> c;
    cout << "Enter d: ";
    cin >> d;
    cout << "Enter e: ";
    cin >> e;
    cout << "Enter f: ";
    cin >> f;

    // Calculate the determinant
    double determinant = a * e - b * d;

    if (determinant == 0)
    {
        cout << "The equations have no unique solution (infinite or no solutions)." << endl;
        // The infinite solution represents coincident lines
        // The no solution represents parallel lines
    }
    else
    {
        // Calculate the values of x and y (i.e. solutions of the linear equation in two variables)
        x = (c * e - b * f) / determinant;
        y = (a * f - c * d) / determinant;

        cout << "Solution:" << endl;
        cout << "x = " << x << endl;
        cout << "y = " << y << endl;
    }
}

void solveLinearEquationInThreeVariables()
{
    double a1, b1, c1, d1, a2, b2, c2, d2, a3, b3, c3, d3;
    double x, y, z;

    cout << "Enter the coefficients and constants for the equations a1x + b1y + c1z = d1, a2x + b2y + c2z = d2, and a3x + b3y + c3z = d3:" << endl;

    cout << "Enter a1: ";
    cin >> a1;
    cout << "Enter b1: ";
    cin >> b1;
    cout << "Enter c1: ";
    cin >> c1;
    cout << "Enter d1: ";
    cin >> d1;
    cout << "Enter a2: ";
    cin >> a2;
    cout << "Enter b2: ";
    cin >> b2;
    cout << "Enter c2: ";
    cin >> c2;
    cout << "Enter d2: ";
    cin >> d2;
    cout << "Enter a3: ";
    cin >> a3;
    cout << "Enter b3: ";
    cin >> b3;
    cout << "Enter c3: ";
    cin >> c3;
    cout << "Enter d3: ";
    cin >> d3;

    if (d1 == 0 && d2 == 0 && d3 == 0)
    {
        cout << "Homogeneous system with at least one solution (trivial solution)." << endl;
        cout << "The trivial solution is (0, 0, 0)." << endl;
    }

    // Calculate the determinants
    double determinant = a1 * (b2 * c3 - b3 * c2) - b1 * (a2 * c3 - a3 * c2) + c1 * (a2 * b3 - a3 * b2);
    double determinantX = d1 * (b2 * c3 - b3 * c2) - b1 * (d2 * c3 - d3 * c2) + c1 * (d2 * b3 - d3 * b2);
    double determinantY = a1 * (d2 * c3 - d3 * c2) - d1 * (a2 * c3 - a3 * c2) + c1 * (a2 * d3 - a3 * d2);
    double determinantZ = a1 * (b2 * d3 - b3 * d2) - b1 * (a2 * d3 - a3 * d2) + d1 * (a2 * b3 - a3 * b2);

    if (determinant == 0)
    {
        // Consistency check
        if (determinantX == 0 && determinantY == 0 && determinantZ == 0)
        {
            cout << "The system is consistent and has infinite solutions." << endl;
            cout << "The values of x, y, and z in terms of t satisfy the third equation." << endl;
            // Show x, y, z in terms of t (assuming c3 is not zero)
            if (c3 != 0)
            {
                cout << "x = " << (d1 - b1) / a1 << "t - " << (c1 / a1) << "t" << endl;
                cout << "y = " << (d2 - a2) / b1 << "t - " << (c2 / b2) << "t" << endl;
                cout << "z = t" << endl;
            }
        }
        else
        {
            cout << "The system is inconsistent and has no solution." << endl;
            cout << "The values of x, y, and z do not satisfy the third equation." << endl;
        }
    }
    else
    {
        // Calculate the values of x, y, and z
        x = determinantX / determinant;
        y = determinantY / determinant;
        z = determinantZ / determinant;

        cout << "Solution:" << endl;
        cout << "x = " << x << endl;
        cout << "y = " << y << endl;
        cout << "z = " << z << endl;
    }
}

void solveLinearEquationInThreeVariables(double a1, double b1, double c1, double d1,
                                         double a2, double b2, double c2, double d2,
                                         double a3, double b3, double c3, double d3)
{
    double x, y, z;

    // Calculate the determinants
    double determinant = a1 * (b2 * c3 - b3 * c2) - b1 * (a2 * c3 - a3 * c2) + c1 * (a2 * b3 - a3 * b2);
    double determinantX = d1 * (b2 * c3 - b3 * c2) - b1 * (d2 * c3 - d3 * c2) + c1 * (d2 * b3 - d3 * b2);
    double determinantY = a1 * (d2 * c3 - d3 * c2) - d1 * (a2 * c3 - a3 * c2) + c1 * (a2 * d3 - a3 * d2);
    double determinantZ = a1 * (b2 * d3 - b3 * d2) - b1 * (a2 * d3 - a3 * d2) + d1 * (a2 * b3 - a3 * b2);

    if (determinant == 0)
    {
        // Consistency check
        if (determinantX == 0 && determinantY == 0 && determinantZ == 0)
        {
            cout << "The system is consistent and has infinite solutions." << endl;
            cout << "The values of x, y, and z in terms of t satisfy the third equation." << endl;
            // Show x, y, z in terms of t (assuming c3 is not zero)
            if (c3 != 0)
            {
                cout << "x = " << (d1 - b1) / a1 << "t - " << (c1 / a1) << "t" << endl;
                cout << "y = " << (d2 - a2) / b1 << "t - " << (c2 / b2) << "t" << endl;
                cout << "z = t" << endl;
            }
        }
        else
        {
            cout << "The system is inconsistent and has no solution." << endl;
            cout << "The values of x, y, and z do not satisfy the third equation." << endl;
        }
    }
    else
    {
        // Calculate the values of x, y, and z
        x = determinantX / determinant;
        y = determinantY / determinant;
        z = determinantZ / determinant;

        cout << "Solution:" << endl;
        cout << "x = " << x << endl;
        cout << "y = " << y << endl;
        cout << "z = " << z << endl;
    }
}

void findEigenValueAndEigenVector()
{
    double a1, a2, a3, b1, b2, b3, c1, c2, c3;

    cout << "Enter the elements of the 3x3 matrix:" << endl;

    // Input for the first row
    cout << "Enter the first row elements a1, a2, a3" << endl;
    cout << "Enter a1: ";
    cin >> a1;
    cout << "Enter a2: ";
    cin >> a2;
    cout << "Enter a3: ";
    cin >> a3;

    // Input for the second row
    cout << "Enter the second row elements b1, b2, b3" << endl;
    cout << "Enter b1: ";
    cin >> b1;
    cout << "Enter b2: ";
    cin >> b2;
    cout << "Enter b3: ";
    cin >> b3;

    // Input for the third row
    cout << "Enter the third row elements c1, c2, c3" << endl;
    cout << "Enter c1: ";
    cin >> c1;
    cout << "Enter c2: ";
    cin >> c2;
    cout << "Enter c3: ";
    cin >> c3;

    // Print the matrix
    cout << "The 3x3 matrix you entered is:" << endl;
    cout << a1 << " " << a2 << " " << a3 << endl;
    cout << b1 << " " << b2 << " " << b3 << endl;
    cout << c1 << " " << c2 << " " << c3 << endl;

    /* Finding the characteristic equation where we subtract 'lambda' from a1, b2, c3
    and then find the determinant*/

    // For Calculating the characteristic equation coefficients
    double a, b, c;
    a = 1.0;
    b = -(a1 + b2 + c3);
    c = a1 * b2 - a2 * b1 + a1 * c3 - a3 * c1 + b2 * c3 - b3 * c2;

    // The equation to find the eigen values is quadratic, and hence to solve it the solutions are
    // [-b + sqrt(b^2 - 4ac)] / 2a and
    // [-b - sqrt(b^2 - 4ac)] / 2a

    // Calculate the discriminant
    cout << "The characteristic equation is represented by:" << endl;
    cout << "L^2 + " << b << "L + " << c << endl;

    double discriminant = b * b - 4 * a * c;

    if (discriminant > 0)
    {
        double lambda1 = (-b + sqrt(discriminant)) / (2 * a);
        double lambda2 = (-b - sqrt(discriminant)) / (2 * a);
        cout << "Eigenvalues: " << lambda1 << " and " << lambda2 << endl;

        // Find the eigenvector corresponding to lambda1
        cout << "Eigenvector for eigenvalue " << lambda1 << ":" << endl;
        solveLinearEquationInThreeVariables(a1 - lambda1, a2, a3, 0, b1, b2 - lambda1, b3, 0, c1, c2, c3 - lambda1, 0);

        // Find the eigenvector corresponding to lambda2
        cout << "Eigenvector for eigenvalue " << lambda2 << ":" << endl;
        solveLinearEquationInThreeVariables(a1 - lambda2, a2, a3, 0, b1, b2 - lambda2, b3, 0, c1, c2, c3 - lambda2, 0);
    }
    else if (discriminant == 0)
    {
        double lambda = -b / (2 * a);
        cout << "Eigenvalue: " << lambda << endl;

        // Find the eigenvector corresponding to lambda
        cout << "Eigenvector for eigenvalue " << lambda << ":" << endl;
        solveLinearEquationInThreeVariables(a1 - lambda, a2, a3, 0, b1, b2 - lambda, b3, 0, c1, c2, c3 - lambda, 0);
    }
    else
    {
        double realPart = -b / (2 * a);
        double imaginaryPart = sqrt(-discriminant) / (2 * a);
        cout << "Eigenvalues: " << realPart << " + " << imaginaryPart << "i and " << realPart << " - " << imaginaryPart << "i" << endl;
    }
}

int main()
{
    int case_number;

    cout << "This algorithm can perform functions like: " << endl;
    cout << "1) Solving linear equations of two variables " << endl;
    cout << "2) Solving linear equations of three variables " << endl;
    cout << "3) Finding eigenvalues and eigenvectors of a 3x3 matrix " << endl;

    cout << "To select a particular operation type" << endl;
    cout << "(1) for Solving linear equations of two variables " << endl;
    cout << "(2) for Solving linear equations of three variables " << endl;
    cout << "(3) for Finding eigenvalues and eigenvectors of a 3x3 matrix " << endl;

    cout << "Enter the value for the corresponding operation: ";
    cin >> case_number;

    switch (case_number)
    {
    case 1:
        solveLinearEquationInTwoVariables();
        break;
    case 2:
        solveLinearEquationInThreeVariables();
        break;
    case 3:
        findEigenValueAndEigenVector();
        break;
    default:
        cout << "Enter a valid case number for the operation" << endl;
        break;
    }

    return 0;
}
