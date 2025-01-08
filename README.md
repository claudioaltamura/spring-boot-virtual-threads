![GitHub Workflow Status (with branch)](https://img.shields.io/github/actions/workflow/status/claudioaltamura/spring-boot-virtual-threads/ci.yml?branch=main)

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# spring-boot-virtual-threads
Spring Boot Virtual Threads example

## Branches

### main

branch without virtual thread configuration

https://github.com/claudioaltamura/spring-boot-virtual-threads/tree/main

### virtual_threads

branch with virtual thread configuration

https://github.com/claudioaltamura/spring-boot-virtual-threads/tree/virtual_threads

## Run
### curl example

    curl -w "%{time_total} s" -o /dev/null -sL http://localhost:8080/calculate/100

## Benchmarking
### simple benchmark tool

    run SimpleBenchmarkingTool.java