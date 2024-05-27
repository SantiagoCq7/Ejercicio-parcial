public interface IFabricaDeTrajes {
    void escribirMenu();
    void añadirComponenteAAlmacen() throws IdException, MuchoExtracomunitarioException, MangaException;
    void listarComponentes();
    void añadirTrajeAAlmacen() throws ColoresException, TallaException, TrajeYaExisteException;
    void listarTrajes();
    void activarDesactivarRebajas();
    void crearEnvio();

    void crearComponentesDePrueba();
}
