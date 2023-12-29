import { connect } from "amqplib";

const connection = await connect("amqp://guest:guest@localhost:5672/")
const channel = await connection.createChannel()

await channel.consume("sms", function (message) {
    console.log(message.fields.routingKey);
    console.log(message.content.toString());
}, {
    noAck: true
})

// await channel.close()
// await connection.close()