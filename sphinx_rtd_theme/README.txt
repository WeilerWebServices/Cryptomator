**************************
Cryptomator Sphinx Theme
**************************

This Sphinx_ theme is a fork of the `Read the Docs Sphinx theme`_, fitted to the color scheme of Cryptomator_.
It was designed to provide a great reader experience for
documentation users on both desktop and mobile devices. This theme is used
primarily on `Read the Docs`_ but can work with any Sphinx project. You can find
a working demo of the theme in the `theme documentation`_

.. _Cryptomator: https://cryptomator.org/
.. _Sphinx: http://www.sphinx-doc.org
.. _Read the Docs: http://www.readthedocs.org
.. _Read the Docs Sphinx theme: https://github.com/readthedocs/sphinx_rtd_theme
.. _theme documentation: https://sphinx-rtd-theme.readthedocs.io/en/latest/

Raw Configuration
=================

This project uses SASS_ to configure the resulting CSS.
For changing the appearance, look into ``src/sass/``.
Each file starts with a comment section explaining the content and purpose.

.. _SASS: https://sass-lang.com/

Build Instructions
==================

Currently the build runs only on linux.
Dependencies are:
* Python 3
* npm
* todo....

If all dependencies are present, run from the project root ``python setup.py clean build_py``.

 The output is written to the directory ``sphinx_rtd_theme``