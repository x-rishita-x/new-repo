#include <iostream>
#include <cmath>

using namespace std;

// Calculate the determinant of a 2x2 matrix
double determinant2x2(double a, double b, double c, double d)
{
    return a * d - b * c;
}

// Calculate the determinant of a 3x3 matrix
double determinant3x3(double a, double b, double c, double d, double e, double f, double g, double h, double i)
{
    return a * determinant2x2(e, f, h, i) - b * determinant2x2(d, f, g, i) + c * determinant2x2(d, e, g, h);
}

// Calculate linear equation of two variables
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

// Calculate linear equation of three variables
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

// Find eigenvalues and eigenvectors of a 2x2 matrix
void findEigenValueAndEigenVector()
{
    double a1, a2, b1, b2;

    cout << "Enter the elements of the 2x2 matrix:" << endl;

    // Input for the first row
    cout << "Enter the first row elements a1, a2" << endl;
    cout << "Enter a1: ";
    cin >> a1;
    cout << "Enter a2: ";
    cin >> a2;

    // Input for the second row
    cout << "Enter the second row elements b1, b2" << endl;
    cout << "Enter b1: ";
    cin >> b1;
    cout << "Enter b2: ";
    cin >> b2;

    // Print the matrix
    cout << "The 2x2 matrix you entered is:" << endl;
    cout << a1 << " " << a2 << endl;
    cout << b1 << " " << b2 << endl;
    cout << endl;
    cout << "The output is only accurate when the characteristic equation is quadratic";
    cout << endl;

    // Calculate coefficients for the characteristic equation
    double a = 1.0;
    double b = -(a1 + b2);
    double c = a1 * b2 - a2 * b1;

    // Calculate the discriminant
    double discriminant = b * b - 4 * a * c;

    // Display the characteristic equation
    cout << "The characteristic equation is represented by:" << endl;
    cout << "x^2 + " << b << "x + " << c << endl;

    if (discriminant > 0)
    {
        double lambda1 = (-b + sqrt(discriminant)) / (2 * a);
        double lambda2 = (-b - sqrt(discriminant)) / (2 * a);
        cout << "Eigenvalues: " << lambda1 << " and " << lambda2 << endl;

        // Find eigenvectors for both eigenvalues
        double v1x = 1;
        double v1y = (lambda1 - a1) / a2;
        cout << "Eigenvector for first eigenvalue " << lambda1 << ": [" << v1x << ", " << v1y << "]" << endl;

        double v2x = 1;
        double v2y = (lambda2 - a1) / a2;
        cout << "Eigenvector for second eigenvalue " << lambda2 << ": [" << v2x << ", " << v2y << "]" << endl;
    }
    else if (discriminant == 0)
    {
        double lambda = -b / (2 * a);
        cout << "Eigenvalue: " << lambda << endl;

        // Find the eigenvector corresponding to lambda
        double v1x = 1;
        double v1y = (lambda - a1) / a2;
        cout << "Eigenvector: [" << v1x << ", " << v1y << "]" << endl;
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
    cout << "3) Finding eigenvalues and eigenvectors of a 2x2 matrix " << endl;
    cout << "4) Finding determinant of a 2x2 matrix " << endl;
    cout << "5) Finding determinant of a 3x3 matrix " << endl;
    cout << endl;
    cout << "To select a particular operation, type" << endl;
    cout << "(1) for Solving linear equations of two variables " << endl;
    cout << "(2) for Solving linear equations of three variables " << endl;
    cout << "(3) for Finding eigenvalues and eigenvectors of a 2x2 matrix " << endl;
    cout << "(4) for Finding determinant of a 2x2 matrix " << endl;
    cout << "(5) for Finding determinant of a 3x3 matrix " << endl;
    cout << endl;
    cout << "Enter the number for the corresponding operation: ";
    cin >> case_number;

    switch (case_number) {
        case 1:
            solveLinearEquationInTwoVariables();
            break;
        case 2:
            solveLinearEquationInThreeVariables();
            break;
        case 3:
            findEigenValueAndEigenVector();
            break;
        case 4: {
            double a, b, c, d;

            cout << "Enter the elements of the 2x2 matrix:" << endl;
            cout << "Enter a: ";
            cin >> a;
            cout << "Enter b: ";
            cin >> b;
            cout << "Enter c: ";
            cin >> c;
            cout << "Enter d: ";
            cin >> d;

            double result = determinant2x2(a, b, c, d);

            cout << "Determinant of the 2x2 matrix is: " << result << endl;
            break;
        }
        case 5: {
            double a1, b1, c1, d1, e1, f1, g1, h1, i1;

            cout << "Enter the elements of the 3x3 matrix:" << endl;
            cout << "Enter a: ";
            cin >> a1;
            cout << "Enter b: ";
            cin >> b1;
            cout << "Enter c: ";
            cin >> c1;
            cout << "Enter d: ";
            cin >> d1;
            cout << "Enter e: ";
            cin >> e1;
            cout << "Enter f: ";
            cin >> f1;
            cout << "Enter g: ";
            cin >> g1;
            cout << "Enter h: ";
            cin >> h1;
            cout << "Enter i: ";
            cin >> i1;

            double result1 = determinant3x3(a1, b1, c1, d1, e1, f1, g1, h1, i1);

            cout << "Determinant of the 3x3 matrix is: " << result1 << endl;
            break;
        }
        default:
            cout << "Enter a valid case number for the operation" << endl;
            break;
    }

    return 0;
}
