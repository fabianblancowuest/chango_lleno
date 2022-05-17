package com.full_monkey.controladores;

import com.full_monkey.entidades.Carrito;
import com.full_monkey.entidades.Compra;
import com.full_monkey.entidades.Perfil;
import com.full_monkey.entidades.Tarjeta;
import com.full_monkey.entidades.Usuario;
import com.full_monkey.servicios.CarritoServicio;
import com.full_monkey.servicios.CompraServicio;
import com.full_monkey.servicios.PerfilServicio;
import com.full_monkey.servicios.TarjetaServicio;
import com.full_monkey.servicios.UsuarioServicio;
import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
@RequestMapping("/compra")
public class CompraControladores {

    @Autowired
    private CompraServicio compraServicio;
    @Autowired
    private CarritoServicio cs;
    @Autowired
    private PerfilServicio ps;
    @Autowired
    UsuarioServicio us;
    @Autowired
    private TarjetaServicio ts;

    @GetMapping("/crearCompra")
    public String crearCompra(HttpSession session, ModelMap model) {
        Usuario user = (Usuario) session.getAttribute("usuariosession");
        Usuario u = us.findById(user.getId());
        Perfil p = u.getPerfil();
        Carrito carro = p.getPendiente();
        model.addAttribute("perfil", p);
        model.addAttribute("carro", carro);
        return "DetallesPreCompra.html";
    }

    @PostMapping("/crearCompra")
    public String llenarCompra(@RequestParam String carro, @RequestParam String idmetodopago, ModelMap model, HttpSession session) {
        try {

            Compra c = compraServicio.crearCompra(cs.buscarId(carro), ts.findById(idmetodopago));
            model.addAttribute("compra", c);
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            Perfil p = ps.findById(u.getPerfil().getId());
            ps.agregarCompra(p.getId(), c);
            model.addAttribute("perfil", p);
            ps.cambiarCarrito(p, cs.crearCarrito());
            return "CompraFinalizada.html";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/info/{id}")
    public String compraFinal(@PathVariable String id, Model model, HttpSession session) {
        try {
            Compra c = compraServicio.buscarPorId(id);
            model.addAttribute("compra", c);
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            model.addAttribute("perfil", u);
            return "detallesCompra.html";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @GetMapping("/{id}")
    public String mostarCompra(@PathVariable String id, Model model, HttpSession session) throws Exception {
        Compra compra = compraServicio.buscarPorId(id);
        Usuario user = (Usuario) session.getAttribute("usuariosession");
        Usuario u = us.findById(user.getId());
        Perfil p = ps.findById(u.getPerfil().getId());
        model.addAttribute("perfil", p);
        model.addAttribute("compra", compra);
        return "CompraFinalizada.html";
    }

//    @GetMapping("/editar/{id}")
//    public String editarCompra(@PathVariable String id, Model model) {
//        try {
//            Compra compra = compraServicio.buscarPorId(id);
//            model.addAttribute("compra", compra);
//            return "editar compra";
//        } catch (Exception exc) {
//            exc.printStackTrace();
//            return "redirect:/compra";
//        }
//    }
//    @GetMapping("/eliminar/{id}")
//    public String eliminarCompra(@PathVariable String id) {
//        Compra compra = compraServicio.buscarPorId(id);
//        compraServicio.eliminar(compra);
//        return "redirect:/compra";
//    }
//    @PostMapping("/editar/{id}")
//    public String editCompra(String id, Carrito carro, Tarjeta metodopago, Double precio_final) {
//        try {
//            compraServicio.modificar(id, LocalDateTime.now(), carro, metodopago, precio_final);
//            return "redirect:/compra";
//        } catch (Exception exc) {
//            exc.printStackTrace();
//            return "redirect:/compra";
//        }
//    }
    @GetMapping("/historial")
    public String historial(ModelMap model, HttpSession session) throws Exception {
        try {
            Usuario user = (Usuario) session.getAttribute("usuariosession");
            Usuario u = us.findById(user.getId());
            Perfil p = ps.findById(u.getPerfil().getId());
            model.addAttribute("productos", p.getHistorial());
            return "historial_de_compras.html";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }
}
