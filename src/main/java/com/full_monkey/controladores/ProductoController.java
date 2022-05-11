package com.full_monkey.controladores;
import com.full_monkey.entidades.Producto;
import com.full_monkey.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping(path = "producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @RequestMapping(value="/{nombre}",method = RequestMethod.GET)
    public ModelAndView listaDeProductosConNombre(@PathVariable String nombre){
        List<Producto> listaEncontrados =  productoService.findByNombre(nombre);
        ModelMap modelo = new ModelMap();
        modelo.addAttribute("productos",listaEncontrados);
        return new ModelAndView("home",modelo);
    }

    @RequestMapping(value = "categoria/{categoria}",method = RequestMethod.GET)
    public ModelAndView listaDeProductosPorCategoria(@PathVariable String categoria){
        List<Producto> listaEncontrados =  productoService.finByCategoria(categoria);
        ModelMap modelo = new ModelMap();
        modelo.addAttribute("productos",listaEncontrados);
        return new ModelAndView("home",modelo);
    }

    @RequestMapping(value = "eliminar/{id}", method=RequestMethod.DELETE)
    public void borrarProducto(@RequestParam String id) throws Exception {
            productoService.eliminarProducto(id);
    }

    @RequestMapping(value = "/crear",method = RequestMethod.POST)
    public ModelAndView productoCrear(@ModelAttribute("producto")  Producto producto) throws Exception {
        productoService.crearProducto(producto);
        return new ModelAndView("creado");
    }
    @RequestMapping(value = "/actualizar/{id}",method = RequestMethod.GET)
    public ModelAndView productoActualizar(@RequestParam String id) throws Exception {
        ModelMap modelo = new ModelMap();
        modelo.put("producto",productoService.getOne(id));
        return new ModelAndView("actualizar",modelo);
    }

    @RequestMapping(value = "/productoActualizado",method = RequestMethod.PUT)
    public ModelAndView procesarActualizacion(@ModelAttribute("producto")  Producto producto) throws Exception {
        productoService.modificarProducto(producto);
        return new ModelAndView("actualizado");
    }

}
