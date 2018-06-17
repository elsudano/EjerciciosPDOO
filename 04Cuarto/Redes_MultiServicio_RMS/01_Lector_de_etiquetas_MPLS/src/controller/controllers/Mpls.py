#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Lista de controladores del programa.

En este fichero podemos encontrarnos todos los controladores,
de todas las vistas de nuestro programa.
"""

from src.controller.Controller import Controller, Path, pcapng
from src.view_app.View import filediag
from src.model.models import *
from src.controller.controllers import *
from src.view_app.views import *

class MplsController(Controller):
    """Gestión de eventos para la ventana de los ejercicios del tema 1
    Con esta controlaremos cada uno de los codigos que tenemos en las
    diapositivas del tema 1
    """
    def back(self, event):
        model = MainModel()
        controller = MainController(self._window, model)
        view = MainView(self._window, controller)
        # Ponemos el tirulo a la nueva vista
        self._window.set_title("Lector de paquetes")
        view.init_view()

    def open_pcapng(self, event):
        filename = "/home/usuario/nextCloud/Facultad/02_Redes_MultiServicio_RMS-17-18/Trabajos_entregados/09_Identificar_fuentes_(servidores)_y_tipo_de_trafico/capture.pcapng"
        if not filename:
            filename = filediag.askopenfilename(initialdir='$USER',
                                                title="Selecciona el fichero a estudiar",
                                                filetypes=(("Network Captured files", "*.pcapng"),
                                                           ("all files", "*.*"))
                                                )
        filename = Path(filename)
        pcap_fp = open(filename, 'wb')
        print (pcap_fp)
        #shb_opts = [pcapng.option.ShbHardware("Dell"), pcapng.option.ShbOs("Ubuntu"), pcapng.option.ShbUserAppl("IntelliJ Idea")]