if(localStorage.getItem("theme") == "bright") {
	document.documentElement.style.setProperty('--current-special-font', 'var(--bright-special-font)');
    document.documentElement.style.setProperty('--current-background', 'var(--bright-background)');
    document.documentElement.style.setProperty('--current-aside', 'var(--bright-aside)');
    document.documentElement.style.setProperty('--current-basic-font', 'var(--bright-basic-font)');
    document.documentElement.style.setProperty('--current-btn', 'var(--pastel-yellow)');
    document.documentElement.style.setProperty(' --current-btn-hover', 'var(--pastel-red)');
    document.documentElement.style.setProperty('--current-aside-2', 'var(--bright-aside-2)');
}
else if(localStorage.getItem("theme") == "dark") {
	document.documentElement.style.setProperty('--current-special-font', 'var(--dark-special-font)');
    document.documentElement.style.setProperty('--current-background', 'var(--dark-background)');
    document.documentElement.style.setProperty('--current-aside', 'var(--dark-aside)');
    document.documentElement.style.setProperty('--current-basic-font', 'var(--dark-basic-font)');
    document.documentElement.style.setProperty('--current-btn', 'var(--pastel-skyblue)');
    document.documentElement.style.setProperty(' --current-btn-hover', 'var(--pastel-blue)');
    document.documentElement.style.setProperty('--current-aside-2', 'var(--dark-aside-2)');
}