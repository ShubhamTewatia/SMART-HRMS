(function(){
 const root=document.documentElement;
 const systemTheme=()=>window.matchMedia('(prefers-color-scheme: dark)').matches?'dark':'light';
 const applyTheme=(mode)=>{root.setAttribute('data-theme',mode==='system'?systemTheme():mode);localStorage.setItem('hrms-theme',mode);document.querySelectorAll('[data-theme-select]').forEach(s=>s.value=mode);};
 const saved=localStorage.getItem('hrms-theme')||'system';
 applyTheme(saved);
 window.setHRMSTheme=applyTheme;
 const media=window.matchMedia('(prefers-color-scheme: dark)');
 media.addEventListener?.('change',()=>{if((localStorage.getItem('hrms-theme')||'system')==='system') root.setAttribute('data-theme',systemTheme());});
 document.addEventListener('DOMContentLoaded',()=>{
   document.querySelectorAll('[data-theme-select]').forEach(s=>{s.value=localStorage.getItem('hrms-theme')||'system';s.addEventListener('change',e=>applyTheme(e.target.value));});
   const current=location.pathname;
   document.querySelectorAll('.hrms-nav .nav-link').forEach(a=>{if(a.getAttribute('href')===current || (current==='/'&&a.getAttribute('href')==='/')) a.classList.add('active');});
   document.querySelectorAll('[data-confirm]').forEach(el=>el.addEventListener('click',e=>{if(!confirm(el.dataset.confirm))e.preventDefault();}));
 });
})();
