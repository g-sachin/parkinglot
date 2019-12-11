[![UTCoverage]()]()

## Prerequisites
1. java 1.8
2. maven 3.3.9
3. git
3. build (linux)

Java is normally installed by default in windows/linux. If on Windows or Mac,
just google "how to install java on ..."

Use `java --version` to see if you have java installed.


## Setup

Fork 🍴 this Repository. Then clone form nto your machine 💻 :

Run bin/setup.sh to prepare environment:
setup.sh will install maven on system.

```bash
    cd bin
    . ./setup.sh
```
## Development:

Next step is to run program:
```bash
    cd bin
    ./parking_lot.sh <input_file.txt>
```

### Testing
To validate the correcteness of program execute functional test
```bash
    cd bin
    ./run_functional_tests.sh
```

