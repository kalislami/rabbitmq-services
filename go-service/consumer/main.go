package main

import (
	"context"
	"fmt"

	"github.com/rabbitmq/amqp091-go"
)

func main() {
	connection, err := amqp091.Dial("amqp://guest:guest@localhost:5672/")

	if err != nil {
		panic(err)
	}
	defer connection.Close()

	channel, err := connection.Channel()

	if err != nil {
		panic(err)
	}

	ctx:= context.Background()
	emailCustomer, err := channel.ConsumeWithContext(ctx, "email", "consumer-email", true, false, false, false, nil)
	if err != nil {
		panic(err)
	}

	for message := range emailCustomer {
		fmt.Println("Routing key: " + message.RoutingKey)
		fmt.Println("Body: " + string(message.Body))
	}
}