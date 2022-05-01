import matplotlib.pyplot as plt
import numpy as np
from mpl_toolkits.mplot3d import Axes3D


def main():
    # Load the text files with points in each dimension into
    # numpy arrays to plot
    ax = plt.axes(projection='3d')
    x = np.loadtxt('xPoints.txt')
    y = np.loadtxt('yPoints.txt')
    z = np.loadtxt('zPoints.txt')

    # Use scatter3D() to plot the arrays then show
    # or save the final plot
    ax.scatter3D(x, y, z)

    # To save image as png
    # fig.savefig('<filename.png>')

    plt.show()
    print("Plotted")


if __name__ == '__main__':
    main()
