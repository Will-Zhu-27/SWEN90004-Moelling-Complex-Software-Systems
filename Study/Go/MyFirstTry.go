// daisy.go
package main

import (
	"fmt"
)

func main() {
	buff := make(chan int)
	flag := make(chan bool)
	go produce(buff)
	go consume(buff, flag)
	fmt.Println(<-flag)
}

func produce(test chan int) {
	test <- 5
}

func consume(test chan int, flag chan bool) {
	temp := -1
	temp = <-test
	if temp != -1 {
		flag <- true
	}
	fmt.Println(temp)
}
