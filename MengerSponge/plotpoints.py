import matplotlib.pyplot as plt
import numpy as np
from mpl_toolkits.mplot3d import Axes3D


def main():
    # Load the text files with points in each dimension into
    # numpy arrays to plot
    ax = plt.axes(projection='3d')
    x = np.loadtxt('spongex.txt')
    y = np.loadtxt('spongey.txt')
    z = np.loadtxt('spongez.txt')

    # Use scatter3D() to plot the arrays then show
    # or save the final plot
    ax.scatter3D(x, y, z)
    # view from the top
    ax.view_init(elev=90, azim=0)

    # To save image as png
    # fig.savefig('<filename.png>')
    

    plt.show()
    print("Plotted")


if __name__ == '__main__':
    main()
