package com.full_monkey.controladores;

import com.full_monkey.entidades.Categoria;
import com.full_monkey.entidades.Producto;
import com.full_monkey.servicios.CategoriaServicio;
import com.full_monkey.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping(path = "producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaServicio cs;

//    @RequestMapping(value = "/{nombre}", method = RequestMethod.GET)
//    public ModelAndView listaDeProductosConNombre(@PathVariable String nombre) {
//        List<Producto> listaEncontrados = productoService.findByNombre(nombre);
//        ModelMap modelo = new ModelMap();
//        modelo.addAttribute("productos", listaEncontrados);
//        return new ModelAndView("home", modelo);
//    }

//    @RequestMapping(value = "categoria/{categoria}", method = RequestMethod.GET)
//    public ModelAndView listaDeProductosPorCategoria(@PathVariable String categoria) {
//        List<Producto> listaEncontrados = productoService.finByCategoria(categoria);
//        ModelMap modelo = new ModelMap();
//        modelo.addAttribute("productos", listaEncontrados);
//        return new ModelAndView("home", modelo);
//    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "eliminar/{id}", method = RequestMethod.DELETE)
    public void borrarProducto(@PathVariable String id) throws Exception {
        productoService.eliminarProducto(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public ModelAndView prodCrear() throws Exception {
        return new ModelAndView("agregarProducto");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public ModelAndView productoCrear(ModelMap model,@RequestParam String img, @RequestParam String nombre, @RequestParam Double precio, @RequestParam Integer stock, @RequestParam String area, @RequestParam(required = false) String descripcion) throws Exception {
        try{
            productoService.crearProducto(img, nombre, precio, stock, area, descripcion, 0);
        }catch(Exception e){
            e.printStackTrace();
            model.put("error", e.getMessage());
        }
        return new ModelAndView("agregarProducto");
    }

//    @RequestMapping(value = "/actualizar/{id}", method = RequestMethod.GET)
//    public ModelAndView productoActualizar(@RequestParam String id) throws Exception {
//        ModelMap modelo = new ModelMap();
//        modelo.put("producto", productoService.getOne(id));
//        return new ModelAndView("actualizar", modelo);
//    }

//    @RequestMapping(value = "/productoActualizado", method = RequestMethod.PUT)
//    public ModelAndView procesarActualizacion(@ModelAttribute("producto") Producto producto) throws Exception {
//        productoService.modificarProducto(producto);
//        return new ModelAndView("actualizado");
//    }

    @RequestMapping(value = "/listaDeProductos", method = RequestMethod.GET)
    public ModelAndView traerTodosLosProductos(ModelMap modelo) throws Exception {
        try {
            modelo.put("Categorias", cs.findAll());
            modelo.put("listaDeProductos", productoService.listarProductos());
        } catch (Exception e) {
            modelo.put("Error", e.getMessage());
        } finally {
            return new ModelAndView("catalogoPrincipal", modelo);
        }
    }

}
