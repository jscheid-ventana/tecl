# Contributing to the Tulip Exchange API

## Tools

In order to build the project install the dependencies mentioned in the [README](README.md#usage-with-bazel).

## Building and running the examples

Run a full project build with the command:

    $ bazel build //...

The examples can be run with the command:

    $ bazel run examples/<lang>/<example>

e.g.

    $ bazel run examples/node/hello_exchange

## Generate build files

In case proto definitions change, are added or removed run Gazelle to regenerate the BUILD files.

    $ bazel run gazelle

## Linters

We use two linters to format code, be sure to run them before submitting a change.

* [Clang-format](https://clang.llvm.org/docs/ClangFormat.html) for proto files (`bazel run clang_format`)
* [Buildifier](https://github.com/bazelbuild/buildtools) for `.bzl`, `BUILD.bazel` and `WORKSPACE` files (`bazel run buildifier_format`)
