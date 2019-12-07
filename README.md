# Quarkus with Jib

Exploration of using Quarkus with Jib (https://github.com/GoogleContainerTools/jib) for building container images.

## Usage

To build the image, run:

    mvn clean verify jib:dockerBuild

Then run the image via:

    docker run -i --rm -p 8080:8080 quarkus-with-jib 

And open http://localhost:8080/hello in your browser.

## Workarounds

A few workarounds are needed to make this work:

* Jib's containerizingMode=packaged needs to be used in order to have a JAR copied instead of a classes directory
* Jib will take the  main artifact produced by the build and copy that into the image. As Quarkus needs its runner JAR to be copied instead, the `copy-rename-maven-plugin` is used to rename the runner JAR into the expected name
* NOT SOLVED: Jib copies the project dependencies to _app/libs_ within the image, whereas in Quarkus case the files from _target/lib_ should be copied; while this basic "Hello World" example works, I reckon Quarkus' class transformations will be missed in other cases; while I could copy the _target/lib_ contents via Jib's "extra directories" mechanism, I couldn't find a way of having it *not* copy the project dependencies, so classes will be duplicated 

## Next steps needed

* Apply transformed JARs from _target/lib_
* Enable usage of classes directory without the need to build a runner JAR
* Bonus: feed transformed JARs directly to Jib without serializing them to disk

