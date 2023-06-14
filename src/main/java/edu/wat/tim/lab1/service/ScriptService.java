package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.repository.CartEntityRepository;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScriptService {
    private final CartEntityRepository cartEntityRepository;

    @Autowired
    public ScriptService(CartEntityRepository cartEntityRepository){
        this.cartEntityRepository = cartEntityRepository;
    }

    public String exec(String script){
        try(Context context = Context.newBuilder("js").allowAllAccess(true).build()){
            var bindings = context.getBindings("js");
            bindings.putMember("cartEntityRepository", cartEntityRepository);
            return context.eval("js", script).toString();
        } catch(PolyglotException e){
            return e.getMessage();
        }
    }

}
