import ratpack.core.form.Form
import ratpack.core.handling.Context
import ratpack.core.handling.Handler
import ratpack.core.http.TypedData
import ratpack.exec.Promise

import java.nio.file.Path

import static ratpack.groovy.Groovy.ratpack
import static ratpack.core.jackson.Jackson.json

def header_data = "SECRET_DATA"
def person = [title: "Mr", name: "Norman", country: "USA"]

ratpack {
    serverConfig {
//        port 5060
        development true
        json new URL("/home/bva/IdeaProjects/ratpack/config.json")
    }
    handlers {
        get {
            header "CustomHeader", header_data
            render "Hello Otus"
        }
        get("data") {
            render json(person)
        }
        post("adele", new FirstHandler())

        get(":name") {
            render "Hello $pathTokens.name"
        }
        get(":name/:id") {
            render "Hello $pathTokens.name $pathTokens.id"
        }

    }
}

class FirstHandler implements Handler {

    @Override
    void handle(Context ctx) throws Exception {
        Promise<Form> form = ctx.parse(Form.class);
        def path = ctx.getRequest().getPath()
        form.then { body ->
            println "по пути $path пришло сообщение с ${body.get("msg")}"
            ctx.getResponse()
                    .status(202)
                    .sendFile(Path.of("example.md"))
        }



    }
}