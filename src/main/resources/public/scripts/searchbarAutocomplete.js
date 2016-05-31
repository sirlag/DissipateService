var input = document.querySelector("input[name=World]");
autoComplt.enable(input, {
        hintsFetcher : function (v, openList) {
            var hints = [], worlds = ["Adamantoise", "Aegis", "Alexander", "Anima", "Asura", "Atomos", "Bahamut", "Balmung",
                "Behemoth", "Belias", "Brynhildr", "Cactuar", "Carbuncle", "Cerberus", "Chocobo", "Coeurl", "Diabolos",
                "Durandal", "Excalibur", "Exodus", "Faerie", "Famfrit", "Fenrir", "Garuda", "Gilgamesh", "Goblin", "Gungnir",
                "Hades", "Hyperion", "Ifrit", "Ixion", "Jenova", "Kujata", "Lamia", "Leviathan", "Lich", "Malboro",
                "Mandragora", "Masamune", "Mateus", "Midgardsormr", "Moogle", "Odin", "Pandaemonium", "Phoenix", "Ragnarok",
                "Ramuh", "Ridill", "Sargatanas", "Shinryu", "Shiva", "Siren", "Tiamat", "Titan", "Tonberry", "Typhon", "Ultima",
                "Ultros", "Unicorn", "Valefor", "Yojimbo", "Zalera", "Zeromus", "Zodiark"];
            for (var i = 0; i < worlds.length; i++) {
                if (worlds[i].toLowerCase().indexOf(v.toLocaleLowerCase()) >= 0) {
                    hints.push(worlds[i]);
                }
            }
            openList(hints);
        }
    }
);